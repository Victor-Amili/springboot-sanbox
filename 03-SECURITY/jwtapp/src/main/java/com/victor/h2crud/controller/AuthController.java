package com.victor.h2crud.controller;


import com.victor.h2crud.dto.LoginRequest;
import com.victor.h2crud.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request){
        if ("admin".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            String token = jwtService.generateToken(request.getUsername());
            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid Credentials");
    }
}