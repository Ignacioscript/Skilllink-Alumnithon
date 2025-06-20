
# JwtUtils Class Documentation

## Overview

The `JwtUtils` class is a utility component in the authentication module that provides all the necessary logic for handling JWT (JSON Web Token) operations.

This includes:
- Generating access and refresh tokens.
- Extracting claims such as username and expiration.
- Validating token authenticity and expiration.
- Internally managing claim parsing using the `io.jsonwebtoken` library.

---

## Annotations

- `@Component`: Registers the class as a Spring-managed bean.
- `@RequiredArgsConstructor`: Automatically generates a constructor for final fields (although not currently used here).

---

## Properties

### `@Value("${jwt.secret}")` - `secretKey`
The key used to sign and parse the JWT. Should be securely stored (e.g., in environment variables or a config server).

### `@Value("${jwt.expiration}")` - `jwtExpiration`
Expiration time for access tokens (in milliseconds).

### `@Value("${jwt.refresh-token.expiration}")` - `refreshExpiration`
Expiration time for refresh tokens (in milliseconds).

---

## Method: `extractUsername`

### Purpose (extractUsername)
Extracts the username (subject) from a given JWT.

### Parameters (extractUsername)
- `String token`: The JWT from which to extract the subject.

### Returns (extractUsername)
- `String`: The username embedded in the token.

### Example Logic (extractUsername)
```java
public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
}
````

---

## Method: `extractClaim`

### Purpose (extractClaim)

Extracts any claim from a JWT using a custom claim resolver function.

### Parameters (extractClaim)

* `String token`: The JWT.
* `Function<Claims, T> resolver`: A lambda to extract the desired claim.

### Returns (extractClaim)

* `T`: The extracted claim.

### Example Logic (extractClaim)

```java
public <T> T extractClaim(String token, Function<Claims, T> resolver) {
    return resolver.apply(extractAllClaims(token));
}
```

---

## Method: `extractAllClaims`

### Purpose (extractAllClaims)

Parses and returns all claims contained in the JWT.

### Parameters (extractAllClaims)

* `String token`: The token to parse.

### Returns (extractAllClaims)

* `Claims`: All claims from the token payload.

### Example Logic (extractAllClaims)

```java
private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .setSigningKey(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
```

---

## Method: `generateToken`

### Purpose (generateToken)

Generates a standard access token for an authenticated user.

### Parameters (generateToken)

* `UserDetails userDetails`: The authenticated user's details.

### Returns (generateToken)

* `String`: A signed JWT token.

### Example Logic (generateToken)

```java
public String generateToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, jwtExpiration);
}
```

---

## Method: `generateRefreshToken`

### Purpose (generateRefreshToken)

Generates a refresh token for an authenticated user.

### Parameters (generateRefreshToken)

* `UserDetails userDetails`: The user's details.

### Returns (generateRefreshToken)

* `String`: A refresh token with a longer expiration.

### Example Logic (generateRefreshToken)

```java
public String generateRefreshToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
}
```

---

## Method: `buildToken`

### Purpose (buildToken)

Private method to centralize token generation logic for both access and refresh tokens.

### Parameters (buildToken)

* `Map<String, Object> extraClaims`: Any additional claims to include.
* `UserDetails userDetails`: The user's details.
* `long jwtExpiration`: Token lifetime in milliseconds.

### Returns (buildToken)

* `String`: The constructed JWT.

### Example Logic (buildToken)

```java
private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration) {
    return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
}
```

---

## Method: `isTokenValid`

### Purpose (isTokenValid)

Checks if a token is both:

1. Authentically signed.
2. Not expired.

### Parameters (isTokenValid)

* `String token`: JWT token to validate.
* `UserDetails userDetails`: User to match against token.

### Returns (isTokenValid)

* `boolean`: `true` if valid, `false` otherwise.

### Example Logic (isTokenValid)

```java
public boolean isTokenValid(String token, UserDetails userDetails) {
    String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
}
```

---

## Method: `isTokenExpired`

### Purpose (isTokenExpired)

Checks whether the token has expired by comparing the expiration date with the current date.

### Parameters (isTokenExpired)

* `String token`: JWT token to check.

### Returns (isTokenExpired)

* `boolean`: `true` if expired.

### Example Logic (isTokenExpired)

```java
private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}
```

---

## Method: `extractExpiration`

### Purpose (extractExpiration)

Returns the expiration date of a given JWT.

### Parameters (extractExpiration)

* `String token`: The JWT to analyze.

### Returns (extractExpiration)

* `Date`: The expiration time of the token.

### Example Logic (extractExpiration)

```java
private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
}
```

---

## Notes

* Signing and parsing is done using `io.jsonwebtoken` (JJWT).
* Tokens are signed using HMAC SHA-512 (`HS512`).
* A commented-out `getSignInKey()` method suggests potential improvements using `Key` objects and Base64 decoding (useful for stronger key handling in production).
* All expiration values should be configured through environment variables or secure config files.

---

## To-Do

* [ ] Enable stronger key encoding via `getSignInKey()` using `Keys.hmacShaKeyFor`.
* [ ] Add logging or exception handling for invalid/malformed tokens.
* [ ] Add unit tests for all methods in this utility class.

