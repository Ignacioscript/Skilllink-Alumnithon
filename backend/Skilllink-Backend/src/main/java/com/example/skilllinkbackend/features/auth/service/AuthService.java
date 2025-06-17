package com.example.skilllinkbackend.features.auth.service;

import com.example.skilllinkbackend.features.auth.dto.AuthRequest;
import com.example.skilllinkbackend.features.auth.dto.AuthResponse;
import com.example.skilllinkbackend.features.auth.dto.RegisterRequest;
import com.example.skilllinkbackend.features.auth.entity.Role;
import com.example.skilllinkbackend.features.auth.entity.User;
import com.example.skilllinkbackend.features.auth.repository.UserRepository;
import com.example.skilllinkbackend.features.auth.security.UserPrincipal;
import com.example.skilllinkbackend.features.auth.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtils.generateToken(new UserPrincipal(user));
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() !=null ? request.getRole() : Role.USER);
        userRepository.save(user);
        return new AuthResponse(jwtUtils.generateToken(new UserPrincipal(user)));
    }

}
