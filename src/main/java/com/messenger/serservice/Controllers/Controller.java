package com.messenger.serservice.Controllers;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.AuthRequest;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Services.UserService;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/auth")
public class Controller {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public Controller(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthRequest authRequest) {

        try {
            String tokenResponse = authRequest.getToken();
            userService.login(authRequest.getAccount(), String.valueOf(tokenResponse));
            return new TokenResponse(tokenResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }



    }
    @PostMapping("register")
    public TokenResponse register(@RequestBody AuthRequest authRequest,@RequestHeader("Authorization") String authHeader) {


        try {
            String token = authHeader.substring(7);
            AccountDTO accountDTO = authRequest.getAccount();



            userService.register(accountDTO,token );

            return new TokenResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }





}
