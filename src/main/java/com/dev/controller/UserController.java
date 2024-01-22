package com.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> regist(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
        
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
        
    }

}
