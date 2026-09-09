package com.aspire.userservice.controller;

import com.aspire.userservice.service.UserService;
import com.aspire.userservice.service.dto.CommonResponseDTO;
import com.aspire.userservice.service.dto.UserRequestDTO;
import com.aspire.userservice.service.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponseDTO<Long>> insertUser(@RequestBody UserRequestDTO userRequestDTO){

        return new ResponseEntity<>(userService.insertUser(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommonResponseDTO<List<UserResponseDTO>>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponseDTO<UserResponseDTO>> getUser(@PathVariable("userId") Long userId){

        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequestDTO userRequestDTO){

        userService.updateUser(userId, userRequestDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}