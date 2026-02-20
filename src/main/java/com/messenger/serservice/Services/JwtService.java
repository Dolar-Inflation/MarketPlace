package com.messenger.serservice.Services;

import com.messenger.serservice.DTO.AccountDTO;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    private Key getSigningKey(AccountDTO accountDTO) throws NoSuchAlgorithmException {
        String username = accountDTO.getAccountName();

        byte[] hash = java.security.MessageDigest
                .getInstance("SHA-512")
                .digest(username.getBytes(StandardCharsets.UTF_8));


        return Keys.hmacShaKeyFor(hash);
    }



    public boolean validateToken(String token, AccountDTO accountDTO) throws NoSuchAlgorithmException {
        try {

//            Key key= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(getSigningKey(accountDTO)).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException | NoSuchAlgorithmException e ) {
            log.warn("{}валидация токена не прошла", e.getMessage());
            return false;
        }

    }
    public String generateToken(AccountDTO account) throws NoSuchAlgorithmException {

        return Jwts.builder()
                .setSubject(account.getAccountName())

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSigningKey(account),SignatureAlgorithm.HS512)
                .compact();

    }

    public String ExtractClaim(String token,AccountDTO accountDTO) throws NoSuchAlgorithmException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(accountDTO))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); }





}
