package com.messenger.senduserdata.Controller;

import com.messenger.senduserdata.Configuration.TokenResponse;
import com.messenger.senduserdata.DTO.AccountDTO;
import com.messenger.senduserdata.DTO.AuthRequest;
import com.messenger.senduserdata.Services.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendUserDataController {

    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    public SendUserDataController(RestTemplate restTemplate, JwtService jwtService) {
        this.restTemplate = restTemplate;
        this.jwtService = jwtService;
    }
@PostMapping("/send/register")
    public TokenResponse sendUserDataRegister(@RequestBody AccountDTO accountDTO) {
    String token = jwtService.generateToken(accountDTO);
//    tokenResponse.setToken(jwtService.generateToken(accountDTO));
    AuthRequest authRequest = new AuthRequest(accountDTO,token);
    TokenResponse tokenResponse = restTemplate.postForObject("http://ser-service/auth/register", authRequest, TokenResponse.class);

     return tokenResponse;
    }
@PostMapping("/send/login")
    public TokenResponse sendUserDataLogin(@RequestBody AccountDTO accountDTO) {
        String token = jwtService.generateToken(accountDTO);
        AuthRequest authRequest = new AuthRequest(accountDTO,token);
        TokenResponse tokenResponse =restTemplate.postForObject("http://ser-service/auth/login", authRequest, TokenResponse.class);
        return tokenResponse;
    }

}
