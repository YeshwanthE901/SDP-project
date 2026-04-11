package com.homevalueplus.service;

import com.homevalueplus.config.JwtUtil;
import com.homevalueplus.dto.AdminLoginRequest;
import com.homevalueplus.dto.AdminLoginResponse;
import com.homevalueplus.entity.Admin;
import com.homevalueplus.exception.UnauthorizedException;
import com.homevalueplus.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AdminLoginResponse login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(admin.getUsername());

        return AdminLoginResponse.builder()
                .token(token)
                .username(admin.getUsername())
                .email(admin.getEmail())
                .message("Login successful")
                .build();
    }
}
