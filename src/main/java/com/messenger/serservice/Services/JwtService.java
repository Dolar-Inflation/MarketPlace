package com.messenger.serservice.Services;

import com.messenger.serservice.DTO.AccountDTO;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JwtService {

    private Key getSigningKey(AccountDTO accountDTO) throws NoSuchAlgorithmException {
        String username = accountDTO.getAccountName();

        byte[] hash = java.security.MessageDigest
                .getInstance("SHA-512")
                .digest(username.getBytes(StandardCharsets.UTF_8));


        return Keys.hmacShaKeyFor(hash);
    }



    public void validateToken(String token, AccountDTO accountDTO) {
        try {

//            Key key= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(getSigningKey(accountDTO)).build().parseClaimsJws(token);
        }
        catch (JwtException | NoSuchAlgorithmException ignored) {
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
                .setSigningKey(getSigningKey((AccountDTO) getSigningKey(accountDTO)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); }


}
