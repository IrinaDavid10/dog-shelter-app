package com.dogshelter.dog_shelter_app.configuration.db.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    @Value("${app.secret.key}")
    private String secretKey;
    @Value("${jwt.expiration:86400}")
    private long expiration;

    public String generateToken(String username, Set<String> roles) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer("Dog Shelter App")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(expiration)))
                .signWith(key)
                .compact();
    }

    public List<String> getRolesFromToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Object rolesObj = claims.get("roles");
            if (rolesObj instanceof Collection) {
                return new ArrayList<>((Collection<String>) rolesObj);
            }
            return new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }



    public String getSubject(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean isValidToken(String token, String username) {
        String tokenUsername = getSubject(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
}

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDate(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

}