package com.app.todoapp.security.service;

import com.app.todoapp.entity.User;
import com.app.todoapp.enums.Role;
import com.app.todoapp.models.request.LoginRequestBody;
import com.app.todoapp.models.request.RegisterRequestBody;
import com.app.todoapp.models.response.other.success.LoginResponseBody;
import com.app.todoapp.models.response.other.success.RegisterResponseBody;
import com.app.todoapp.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseBody register(RegisterRequestBody request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return RegisterResponseBody.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponseBody authenticate(LoginRequestBody request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponseBody.builder()
                .token(jwtToken)
                .build();
    }
}
