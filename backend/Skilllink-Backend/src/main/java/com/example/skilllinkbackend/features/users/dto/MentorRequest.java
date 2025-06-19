package com.example.skilllinkbackend.features.users.dto;

import com.example.skilllinkbackend.features.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorRequest {
    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String bio;
    private String experience;
    private String education;
    //private List<String> skills;
}
