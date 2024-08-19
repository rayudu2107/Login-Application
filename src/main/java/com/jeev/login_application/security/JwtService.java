package com.jeev.login_application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Secret key used for signing the JWT. The key is generated once using the getSigning method.
    private final Key SECRET_KEY = getSigning();

  
    // Generates a JWT token for the given user details.
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) 
                .compact(); 
    }

    // Extracts the username (subject) from the JWT token.
    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Extracts a specific claim from the JWT token using a provided claims resolver function.
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    }
  
    // Extracts all claims from the JWT token.
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
    }
  
    // Generates a secret key used for signing the JWT tokens.
    private Key getSigning() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secret key for HS256 algorithm
    }

    // Retrieves the expiration date from the JWT token.
    public Date getExpirationDateFromToken(String token) {
        return extractClaims(token, Claims::getExpiration); // Extract the expiration date from the token claims
    }

    // Checks if the JWT token is expired.
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token); 
        return expiration.before(new Date()); 
    }

    // Validates the JWT token.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token); 
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    }
}
