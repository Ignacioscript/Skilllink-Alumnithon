package com.example.skilllinkbackend.features.auth.dto;


import com.example.skilllinkbackend.features.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private Role role;
}
