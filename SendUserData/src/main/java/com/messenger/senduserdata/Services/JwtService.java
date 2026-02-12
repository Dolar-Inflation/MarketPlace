package com.messenger.senduserdata.Services;

import com.messenger.senduserdata.DTO.AccountDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AccountDTO account) {

        return Jwts.builder()
                .setSubject(account.getAccountName())

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith( getSigningKey(),SignatureAlgorithm.HS512)
                .compact();

    }
}
