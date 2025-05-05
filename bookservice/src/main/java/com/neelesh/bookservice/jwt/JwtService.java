package com.neelesh.bookservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "tCt7VSUioBALpNTOf0FtQwilFSClXwZUjuYkADodl2fZ4Pdk7h2xPf8yR3lyMYwa\n" +
            "qgbbxLMeVT7S737q5qvs5A==";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    Date now = new Date();
    Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
    String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();

    }

    Claims extractClaims(String token) throws UnsupportedEncodingException {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    boolean isTokenValid(UserDetails userDetails, String token) throws UnsupportedEncodingException {
        return (extractClaims(token).getSubject()).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) throws UnsupportedEncodingException {

        return extractClaims(token).getExpiration().before(new Date());
    }

}
