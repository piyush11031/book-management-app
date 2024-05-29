package com.booknetworkapi.controller;

import com.booknetworkapi.dto.LoginDTO;
import com.booknetworkapi.dto.RegistrationDTO;
import com.booknetworkapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationDTO registration){

        userService.register(registration);
        return new ResponseEntity<>("User Registered", HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO login){

        String token = userService.loginUser(login);

        return new ResponseEntity(token, HttpStatus.OK);

    }
}
