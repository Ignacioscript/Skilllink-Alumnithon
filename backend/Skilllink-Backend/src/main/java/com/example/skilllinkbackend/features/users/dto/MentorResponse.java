package com.example.skilllinkbackend.features.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String bio;
    private String experience;
    private String education;
}
