package com.football.url.service.impl;

import com.football.url.entity.Role;
import com.football.url.entity.User;
import com.football.url.exception.BlogAPIException;
import com.football.url.payload.LoginDto;
import com.football.url.payload.RegisterDto;
import com.football.url.repository.RoleRepository;
import com.football.url.repository.UserRepository;
import com.football.url.security.JwtTokenProvider;
import com.football.url.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        logger.info("login method in service class started");
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getUserNameOrEmail(),
                                loginDto.getPassword()));

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        logger.info("login method in service class ended");
        String token = jwtTokenProvider.generateJwtToken(authentication);
        logger.info("JWT Token is generated");
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        logger.info("Register method in service class started");
        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();

        // Convert UserRole enum to role name and fetch role from repository
        Role userRole = roleRepository.findByName(registerDto.getUserRole().name());
        roles.add(userRole); // Add the user role to the roles set

        user.setRoles(roles); // Assign the roles set to the user's roles
        userRepository.save(user);
        logger.info("Register method in service class ended");
        return "User registered successfully";
    }
}
