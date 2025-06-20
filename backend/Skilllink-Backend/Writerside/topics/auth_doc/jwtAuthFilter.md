

# JwtAuthFilter Class Documentation

## Overview

The `JwtAuthFilter` class is a custom filter that extends `OncePerRequestFilter` and intercepts every incoming HTTP request to validate the presence and authenticity of a JWT (JSON Web Token). It ensures that authenticated users are recognized by Spring Security during the request lifecycle.

This class is essential to enable **stateless JWT-based authentication** in your Spring Boot application.

---

## Annotations

- `@Component`: Marks the class as a Spring-managed bean, allowing it to be automatically detected and injected.
- `@RequiredArgsConstructor`: Generates a constructor for the `userService` and `jwtUtils` dependencies.

---

## Dependencies

- **UserService**: Provides user details required for authentication.
- **JwtUtils**: Utility class responsible for extracting usernames and validating JWTs.

---

## Method: `doFilterInternal`

### Purpose (doFilterInternal)
Intercepts each HTTP request to:
- Extract the JWT from the `Authorization` header.
- Validate the token.
- If valid, authenticate the user by setting the authentication in the `SecurityContext`.

### Workflow (doFilterInternal)
1. Extract the token from the HTTP request.
2. If a token exists, extract the username.
3. If the username is valid and no authentication is present in the context:
   - Load the user details from the `UserService`.
   - Validate the token.
   - If valid, create an `Authentication` object.
   - Set the authentication in the `SecurityContext`.
4. Continue the filter chain.

### Parameters (doFilterInternal)
- `HttpServletRequest request`: Incoming HTTP request.
- `HttpServletResponse response`: HTTP response object.
- `FilterChain filterChain`: Chain of remaining filters to continue processing.

### Example Logic (doFilterInternal)
```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    String token = getTokenFromRequest(request);

    if (token != null) {
        String username = jwtUtils.extractUsername(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtUtils.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }

    filterChain.doFilter(request, response);
}
````

---

## Method: `getTokenFromRequest`

### Purpose (getTokenFromRequest)

Extracts the JWT token from the `Authorization` header, if it follows the `Bearer <token>` format.

### Workflow (getTokenFromRequest)

1. Retrieve the value of the `Authorization` header.
2. Check if the value starts with `"Bearer "`.
3. Return the token substring if valid, or `null` otherwise.

### Parameters (getTokenFromRequest)

* `HttpServletRequest request`: The incoming request to extract the token from.

### Returns (getTokenFromRequest)

* `String`: A valid JWT if present, otherwise `null`.

### Example Logic (getTokenFromRequest)

```java
private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
        return bearerToken.substring(7);
    }
    return null;
}
```

---

## Security Context Integration

The filter checks the `SecurityContextHolder` to avoid re-authenticating already authenticated requests. If the JWT is valid, it sets an authentication token, enabling Spring Security to authorize the user during the request.

---

## Notes

* This filter is automatically invoked for each request as part of the Spring Security filter chain.
* It is registered in `SecurityConfig` using `.addFilterBefore(...)`.
* Stateless JWT authentication removes the need for server-side session storage.

---
