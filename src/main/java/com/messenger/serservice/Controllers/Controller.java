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
    public TokenResponse login(@RequestBody AuthRequest authRequest,@RequestHeader("Authorization") String authHeader) {

        try {
            String token = authHeader.substring(7);
            AccountDTO accountDTO = authRequest.getAccount();
            userService.login(accountDTO, token);
            return new TokenResponse(token);
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
            System.out.println("тело и заголовок"+ authRequest+token);
            return new TokenResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }





}
