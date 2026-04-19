package com.trung.userservice.security.jwt;

import com.trung.userservice.entity.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Users users) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);

        List<String> roles = users.getUserRoles().stream()
                .map(userRoles -> userRoles.getRoleId().getName().toUpperCase())
                .toList();

        return Jwts.builder()
                .setSubject(users.getEmail())
                .setIssuedAt(new Date())
                .claim("role", roles)
                .setExpiration(expirationDate)
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();
    }
}
