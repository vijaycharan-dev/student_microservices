package com.aspire.authservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

    private  final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;
        try {
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
                jwt = authorizationHeader.substring(7);
                userName = jwtTokenUtil.getUserNameFromToken(jwt);
                if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtTokenUtil.validateToken(jwt)){
                    List<GrantedAuthority> authList = new ArrayList<>();
                    authList.add(new SimpleGrantedAuthority(jwtTokenUtil.getRoleFromToken(jwt).name()));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,null,authList);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }

            }
            filterChain.doFilter(request,response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");

        }

    }
}
