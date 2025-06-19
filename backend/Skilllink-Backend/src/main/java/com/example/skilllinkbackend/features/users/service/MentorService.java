package com.example.skilllinkbackend.features.users.service;

import com.example.skilllinkbackend.features.auth.entity.Role;
import com.example.skilllinkbackend.features.auth.entity.User;
import com.example.skilllinkbackend.features.auth.repository.UserRepository;
import com.example.skilllinkbackend.features.users.dto.MentorRequest;
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
    // Add methods to handle mentor-related operations, such as creating a mentor profile,

    public Mentor createOrLinkMentor(MentorRequest request) {
        // Check if the User exists
        String username = request.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> { // If user doesn't exist, create one
                    User newUser = new User();
                    newUser.setUsername(username);
                    newUser.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
                    newUser.setRole(Role.MENTOR); // Assuming an ENUM or constant for roles
                    return userRepository.save(newUser);
                });

        // Create the Mentor and associate it with the User
        Mentor mentor = new Mentor();
        mentor.setUser(user);
        mentor.setFirstName(request.getFirstName());
        mentor.setLastName(request.getLastName());
        mentor.setEmail(request.getEmail());
        mentor.setPhoto(request.getPhoto());
        mentor.setBio(request.getBio());
        mentor.setExperience(request.getExperience());
        mentor.setEducation(request.getEducation());

        return mentorRepository.save(mentor);
    }


    public Mentor getMentorById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Mentor updateMentor(Long id, MentorRequest request) {
        Mentor mentor = getMentorById(id);
        // Update mentor details
        mentor.setFirstName(request.getFirstName());
        mentor.setLastName(request.getLastName());
        mentor.setEmail(request.getEmail());
        mentor.setPhoto(request.getPhoto());
        mentor.setBio(request.getBio());
        mentor.setExperience(request.getExperience());
        mentor.setEducation(request.getEducation());

        return mentorRepository.save(mentor);
    }

    public void deleteMentorById(Long id) {
        Mentor mentor = getMentorById(id);
        mentorRepository.delete(mentor);
    }
}
