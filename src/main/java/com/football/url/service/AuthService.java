package com.football.url.service;

import com.football.url.payload.LoginDto;
import com.football.url.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
