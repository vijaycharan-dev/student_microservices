package com.aspire.authservice.config;

import com.aspire.authservice.dao.UserEntityRepository;
import com.aspire.authservice.dao.model.Role;
import com.aspire.authservice.dao.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private final UserEntityRepository userEntityRepository;
    private static final long EXPEIRED_DURATION = 30L * 24 * 60 * 60 *1000 ;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateAccessToken(UserEntity userEntity){
        return Jwts.builder()
                .setSubject(String.format("%s,%s", userEntity.getUserId(),userEntity.getEmailId()))
                .setIssuer("vijay-auth-service")
                .claim("name",userEntity.getUsername())
                .claim("role",userEntity.getRole())
                .claim("firstName",userEntity.getFirstName())
                .claim("userId",userEntity.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPEIRED_DURATION))
                .signWith(getSecretKey())
                .compact();
    }

    @SuppressWarnings("deprecation")
    private Claims getAllClaimsFromToken(String token){

        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();

    }

    private <T  > T  getClaimFromToken(String token , Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    public String getUserNameFromToken(String token){

        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("name");

    }

    public Date getExpirationDateToken(String token){

        return getClaimFromToken(token,Claims::getExpiration);

    }

    public Role getRoleFromToken(String token){

        return (Role) getAllClaimsFromToken(token).get("role");

    }
    private Boolean isTokenExpired(String token){

        Date expiration = getExpirationDateToken(token);
        return expiration.before(new Date());

    }
    public Boolean validateToken(String token ){

        String username = getUserNameFromToken(token);
        UserEntity userEntity = null;
        Optional < UserEntity > user = userEntityRepository.findByUsername(username);

        if(user.isPresent()){
            userEntity = user.get();

        }

        if(userEntity != null || isTokenExpired(token)){
            return true;
        }

        return false;
    }


}
