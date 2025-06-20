


# SecurityConfig Class Documentation

## Overview

The `SecurityConfig` class is a Spring Security configuration class annotated with `@Configuration` and `@EnableWebSecurity`. It defines the security policies for the SkillLink backend application, including HTTP request authorization, session policy, and password encoding.

This configuration ensures:
- Stateless session management for RESTful APIs.
- Integration of a custom JWT authentication filter (`JwtAuthFilter`).
- Role-based access control for endpoints.
- Secure password hashing using BCrypt.

---

## Annotations

- `@Configuration`: Declares this class as a source of Spring bean definitions.
- `@EnableWebSecurity`: Enables Spring Security’s web security support.
- `@RequiredArgsConstructor`: Generates a constructor for the final field `jwtAuthFilter`.

---

## Dependencies

- **JwtAuthFilter**: A custom filter that validates JWTs in incoming requests.
- **BCryptPasswordEncoder**: Used to hash passwords securely before persisting them.

---

## Method: `filterChain(HttpSecurity http)`

### Purpose (filterChain)
Defines the core HTTP security rules for the backend API. Configures endpoint access, disables CSRF for stateless API usage, adds JWT filtering, and sets session policy.

### Workflow (filterChain)
1. Disable CSRF as it’s not required for stateless REST APIs.
2. Set session management to `STATELESS` to avoid server-side sessions.
3. Register `JwtAuthFilter` to intercept requests before Spring’s authentication filter.
4. Configure endpoint access:
    - Allow public access to `/api/auth/**`, `/api/mentors/**`, `/mentors/**`, and `/`.
    - Restrict `/api/admin/**` to users with `ADMIN` role.
    - Require authentication for all other endpoints.

### Parameters (filterChain)
- `HttpSecurity http`: The main object used to configure web-based security for specific HTTP requests.

### Returns (filterChain)
- `SecurityFilterChain`: The configured security filter chain for the application.

### Example Logic (filterChain)
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/mentors/**", "/mentors/**", "/").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
}
````

---

## Method: `passwordEncoder()`

### Purpose (passwordEncoder)

Provides a `PasswordEncoder` bean that uses the BCrypt hashing algorithm. It is used throughout the application to securely store and verify passwords.

### Workflow (passwordEncoder)

1. Instantiate a `BCryptPasswordEncoder`.
2. Return it as a Spring-managed bean.

### Returns (passwordEncoder)

* `PasswordEncoder`: A bean for encoding and matching passwords using the BCrypt algorithm.

### Example Logic (passwordEncoder)

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

## Notes

* **JWT Authentication**: The `JwtAuthFilter` is injected and added *before* Spring's built-in `UsernamePasswordAuthenticationFilter` to ensure token validation occurs first.
* **Session Management**: Stateless sessions are crucial for REST APIs to scale and avoid maintaining server state.
* **Authorization**: The use of `.requestMatchers()` enables fine-grained control over route protection and is future-proof with Spring Security 6.

---

