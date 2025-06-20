package com.example.skilllinkbackend.features.users.service;

import com.example.skilllinkbackend.features.auth.entity.Role;
import com.example.skilllinkbackend.features.auth.entity.User;
import com.example.skilllinkbackend.features.auth.repository.UserRepository;
import com.example.skilllinkbackend.features.users.dto.MentorRequest;
import com.example.skilllinkbackend.features.users.dto.MentorResponse;
import com.example.skilllinkbackend.features.users.entities.Mentor;
import com.example.skilllinkbackend.features.users.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * For full documentation, see:
 * @see <a href="Writerside/topics/mentor-service-doc.md">Writerside/topics/mentor-service-doc.md</a>
 */
@Service
@RequiredArgsConstructor
public class MentorService {

    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;
    private final PasswordEncoder passwordEncoder;


//    public MentorResponse createOrLinkMentor(MentorRequest request) {
//        String username = request.getUsername();
//        User user = userRepository.findByUsername(username)
//                .orElseGet(() -> {
//                    User newUser = new User();
//                    newUser.setUsername(username);
//                    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//                    newUser.setRole(Role.MENTOR);
//                    return userRepository.save(newUser);
//                });
//
//        Mentor mentor = new Mentor();
//        mentor.setUser(user);
//        mentor.setFirstName(request.getFirstName());
//        mentor.setLastName(request.getLastName());
//        mentor.setEmail(request.getEmail());
//        mentor.setPhoto(request.getPhoto());
//        mentor.setBio(request.getBio());
//        mentor.setExperience(request.getExperience());
//        mentor.setEducation(request.getEducation());
//        mentorRepository.save(mentor);
//
//        return mapToMentorResponse(mentor);
//    }

    public MentorResponse createOrLinkMentor(MentorRequest request) {
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(request.getUsername().trim());
                    newUser.setPassword(passwordEncoder.encode(request.getPassword().trim()));
                    newUser.setRole(Role.MENTOR);
                    return userRepository.save(newUser);
                });

        Mentor mentor = mapToMentor(request);
        mentor.setUser(user);
        Mentor savedMentor = mentorRepository.save(mentor);

        return mapToMentorResponse(savedMentor);
    }


    public MentorResponse getMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
        return mapToMentorResponse(mentor);
    }

    public List<MentorResponse> getAllMentors() {
        return mentorRepository.findAll().stream()
                .map(this::mapToMentorResponse)
                .toList();
    }

    public MentorResponse updateMentor(Long id, MentorRequest request) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
        // Update mentor details
        mentor.setFirstName(request.getFirstName());
        mentor.setLastName(request.getLastName());
        mentor.setEmail(request.getEmail());
        mentor.setPhoto(request.getPhoto());
        mentor.setBio(request.getBio());
        mentor.setExperience(request.getExperience());
        mentor.setEducation(request.getEducation());
        mentorRepository.save(mentor);
        return mapToMentorResponse(mentor);
    }

    public void deleteMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
        mentorRepository.delete(mentor);
    }


    //Helper methods to map between DTOs and entities
    private Mentor mapToMentor(MentorRequest request) {
        Mentor mentor = new Mentor();
        mentor.setFirstName(request.getFirstName());
        mentor.setLastName(request.getLastName());
        mentor.setEmail(request.getEmail());
        mentor.setPhoto(request.getPhoto());
        mentor.setBio(request.getBio());
        mentor.setExperience(request.getExperience());
        mentor.setEducation(request.getEducation());
        return mentor;
    }

    private MentorResponse mapToMentorResponse(Mentor mentor) {
        MentorResponse response = new MentorResponse();
        response.setId(mentor.getId());
        response.setUsername(mentor.getUser().getUsername());  // Map username from User entity
        response.setFirstName(mentor.getFirstName());
        response.setLastName(mentor.getLastName());
        response.setEmail(mentor.getEmail());
        response.setPhoto(mentor.getPhoto());
        response.setBio(mentor.getBio());
        response.setExperience(mentor.getExperience());
        response.setEducation(mentor.getEducation());
        return response;
    }
}
