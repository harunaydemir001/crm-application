package com.jekirdek.authentication_service.controller;

import com.jekirdek.authentication_service.model.AuthRequest;
import com.jekirdek.authentication_service.service.AuthService;
import com.jekirdek.common.factory.ResponseFactory;
import com.jekirdek.common.model.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<Response> register(@Parameter(description = "AuthRequest", required = true) @RequestBody AuthRequest authRequest) {
        return ResponseFactory.createResponse(authService.register(authRequest), HttpStatus.CREATED);
    }

}
