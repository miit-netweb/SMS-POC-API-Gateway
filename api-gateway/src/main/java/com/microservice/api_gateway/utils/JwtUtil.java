package com.microservice.api_gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public Claims validateAndGetClaim(final String token){
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(getSignKey()) // Set the signing key used to verify the token's signature
                        .build()
                        .parseClaimsJws(token) // Parse the JWT token
                        .getBody(); // Get the JWT body which contains claims
                return claims;
            } catch (Exception e) {
                // Handle exception (e.g., JWT signature verification failed, token expired, etc.)
                throw new RuntimeException("Failed to parse JWT: " + e.getMessage());
            }
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}