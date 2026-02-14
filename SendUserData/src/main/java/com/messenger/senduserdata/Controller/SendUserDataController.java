package com.messenger.senduserdata.Controller;

import com.messenger.senduserdata.Configuration.TokenResponse;
import com.messenger.senduserdata.DTO.AccountDTO;
import com.messenger.senduserdata.DTO.AuthRequest;
import com.messenger.senduserdata.Services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class SendUserDataController {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;


    public SendUserDataController(RestTemplate restTemplate, JwtService jwtService) {
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;

    }
@PostMapping("/send/register")
    public ResponseEntity<TokenResponse> sendUserDataRegister(@RequestBody AccountDTO accountDTO) {
    String token = jwtService.generateToken(accountDTO);
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

//    tokenResponse.setToken(jwtService.generateToken(accountDTO));
    AuthRequest authRequest = new AuthRequest(accountDTO,token);

    HttpEntity<AuthRequest> entity = new HttpEntity<>(authRequest, headers);

    TokenResponse tokenResponse = restTemplate.postForObject("http://ser-service/auth/register", entity, TokenResponse.class);

    assert tokenResponse != null;
    ResponseCookie cookie = ResponseCookie.from("access", tokenResponse.getToken())
            .httpOnly(true)
            .secure(true)
            .sameSite("Strict")
            .path("/")
            .maxAge(3600)
            .build();

    log.info("Response entity для клиента reg {}", accountDTO.getAccountName());
    log.info("Заголовок reg: Set-Cookie={}", cookie);
    log.info("Тело reg: {}", tokenResponse);

    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE,cookie.toString())
            .body(tokenResponse);
    }
@PostMapping("/send/login")
    public ResponseEntity<TokenResponse> sendUserDataLogin(@RequestBody AccountDTO accountDTO) {
        String token = jwtService.generateToken(accountDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        AuthRequest authRequest = new AuthRequest(accountDTO,token);
        HttpEntity<AuthRequest> entity = new HttpEntity<>(authRequest, headers);

        TokenResponse tokenResponse =restTemplate.postForObject("http://ser-service/auth/login", entity, TokenResponse.class);

    assert tokenResponse != null;
    ResponseCookie cookie = ResponseCookie.from("access", tokenResponse.getToken())
            .httpOnly(true)
            .secure(true)
            .sameSite("Strict")
            .path("/")
            .maxAge(3600)
            .build();

    log.info("Response entity для клиента log {}", accountDTO.getAccountName());
    log.info("Заголовок: log Set-Cookie={}", cookie);
    log.info("Тело: log {}", tokenResponse);


    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE,cookie.toString())
            .body(tokenResponse);
    }

}
