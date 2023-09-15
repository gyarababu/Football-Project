package com.football.url.controller;

import com.football.url.payload.JwtAuthResponse;
import com.football.url.payload.LoginDto;
import com.football.url.payload.RegisterDto;
import com.football.url.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Auth Resource CRUD REST APIs"
)
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Log In or Sign In REST API",
            description = "Login/Signin into the Blog"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        logger.trace("started login or signin for user trace log level ");
        logger.info("started login or signin for user info log level ");
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        // setting the token
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @Operation(
            summary = "Register or Sign Up REST API",
            description = "Saving registered or signed up data into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        logger.trace("started register or signup for user trace log level ");
        logger.info("started register or signup for user info log level ");
        String response = authService.register(registerDto);
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

}
