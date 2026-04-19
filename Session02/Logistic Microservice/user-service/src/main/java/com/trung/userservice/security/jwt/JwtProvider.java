package com.trung.userservice.security.jwt;

import com.trung.userservice.entity.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
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

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, HttpServletRequest request){
        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e) {
            request.setAttribute("error", "ExpiredJwtException");
        }catch (UnsupportedJwtException e) {
            request.setAttribute("error", "UnsupportedJwtException");
        }catch (IllegalArgumentException e) {
            request.setAttribute("error", "IllegalArgumentException");
        }catch (SignatureException e) {
            request.setAttribute("error", "SignatureException");
        }catch (MalformedJwtException e) {
            request.setAttribute("error", "MalformedJwtException");
        }
        return false;
    }
}
