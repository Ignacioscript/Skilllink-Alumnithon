package com.example.skilllinkbackend.features.users.entities;

import com.example.skilllinkbackend.features.auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

        @Id
        private Long id;

        @OneToOne
        @MapsId
        private User user;

        private String firstName;
        private String lastName;
        private String email;
        private String photo;
        private String bio;
        private String experience;
        private String education;
      //  private List<Skill> skills = new ArrayList<>();


}