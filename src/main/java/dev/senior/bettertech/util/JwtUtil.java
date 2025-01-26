package dev.senior.bettertech.util;

import dev.senior.bettertech.BetterTechApplication;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    private Key signingKey;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Initialize the signingKey after Spring injects the secretKey
    private Key getSigningKey() {
        if (signingKey == null) {
            signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        }
        return signingKey;
    }
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public List<SimpleGrantedAuthority> getAuthorities(String role) {
        // Ensure the role in the token matches "ROLE_*"
        return List.of(new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role));
    }

}
