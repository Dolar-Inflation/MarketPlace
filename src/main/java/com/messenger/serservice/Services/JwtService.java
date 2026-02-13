package com.messenger.serservice.Services;

import com.messenger.serservice.Entity.Account;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
//TODO переделать на нормальную валидацию токена
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

//    public String generateToken(Account account) {
//
//        return Jwts.builder()
//                .setSubject(account.getAccountname())
//
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith( getSigningKey(),SignatureAlgorithm.HS512)
//                .compact();
//
//    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException e) {
            return false;
        }
    }

    public String ExtractClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); }


}
