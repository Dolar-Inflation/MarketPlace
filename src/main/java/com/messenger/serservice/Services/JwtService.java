package com.messenger.serservice.Services;

import com.messenger.serservice.Entity.Account;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {


    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Account account) {

        return Jwts.builder()
                .setSubject(account.getAccountname())

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith( key,SignatureAlgorithm.HS512)
                .compact();

    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException e) {
            return false;
        }
    }

    public String ExtractClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); }


}
