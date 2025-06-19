

# MentorService Class Documentation

## Overview

The `MentorService` class is a Spring `@Service` component responsible for managing mentor-related operations in the SkillLink backend. It interacts with the database using `UserRepository` and `MentorRepository` and encapsulates the business logic for creating or linking mentors and retrieving mentor profiles.

This service ensures:
- A user account exists (or is created) when registering a mentor.
- A mentor entity is correctly linked to a user and persisted.
- Mentor data can be retrieved by ID with proper error handling.

---

## Dependencies

The class uses the following injected dependencies:

- `UserRepository`: Interface to manage `User` persistence.
- `MentorRepository`: Interface to manage `Mentor` persistence.
- `PasswordEncoder`: For securely encoding user passwords.

These dependencies are injected using Lombok's `@RequiredArgsConstructor`.

---

## Method: `createOrLinkMentor`

### Purpose (createOrLinkMentor)
Creates a new mentor and links it to an existing user, or creates a new user if one does not already exist, based on the username.

### Workflow (createOrLinkMentor)
1. Check if the user with the provided username exists.
2. If not, create a new `User` with the `MENTOR` role.
3. Create a new `Mentor` entity using data from the `MentorRequest`.
4. Link the mentor to the `User`.
5. Save and return the new `Mentor`.

### Parameters (createOrLinkMentor)
- `MentorRequest request`: DTO containing both user data and mentor-specific data.

### Returns (createOrLinkMentor)
- `Mentor`: The saved mentor entity.

### Example Logic (createOrLinkMentor)
```java
public Mentor createOrLinkMentor(MentorRequest request) {
    String username = request.getUser().getUsername();
    User user = userRepository.findByUsername(username)
            .orElseGet(() -> {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
                newUser.setRole(Role.MENTOR);
                return userRepository.save(newUser);
            });

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
````

---

## Method: `getMentorById`

### Purpose (getMentorById)

Fetches a mentor from the database by its unique ID.

### Workflow (getMentorById)

1. Call `mentorRepository.findById(id)`.
2. If the mentor is not found, throw a `RuntimeException`.

### Parameters (getMentorById)

* `Long id`: The unique identifier of the mentor.

### Returns (getMentorById)

* `Mentor`: The found mentor entity.

### Exceptions (getMentorById)

* Throws `RuntimeException` if the mentor does not exist.

### Example Logic (getMentorById)

```java
public Mentor getMentorById(Long id) {
    return mentorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mentor not found with id: " + id));
}
```

---

## Annotations

* `@Service`: Marks the class as a Spring service for business logic.
* `@RequiredArgsConstructor`: Automatically generates a constructor for final fields.

---

## Related Entities and DTOs

* **User**: Represents the system's users. Required for account linkage.
* **Mentor**: Represents a mentor profile with fields like bio, experience, and education.
* **MentorRequest**: DTO containing combined user and mentor input data.
* **Role**: Enum used to assign roles (e.g., `MENTOR`) to users.

---

## Notes

* This service assumes that each mentor has one and only one linked user.
* Passwords are securely hashed before saving.
* Error handling is basic (`RuntimeException`). You may consider using custom exceptions for production.
