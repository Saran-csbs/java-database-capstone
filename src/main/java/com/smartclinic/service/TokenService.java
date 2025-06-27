package com.smartclinic.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Service for generating and validating JWTs.
 */
@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;               // from application.properties

    @Value("${jwt.expiration}")
    private long expirationMillis;       // e.g. 86400000

    /**
     * Returns a SecretKey derived from the configured secret.
     */
    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT containing the username as subject,
     * with issuedAt and expiration claims.
     */
    public String generateToken(String username) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + expirationMillis))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Parses and validates the token, returning the subject (username/email).
     */
    public String validateToken(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token);
        return jws.getBody().getSubject();
    }
}
