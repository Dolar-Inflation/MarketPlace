package com.messenger.serservice.Controllers;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.AuthRequest;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/auth")
public class Controller {

    private final UserService userService;


    public Controller(UserService userService) {
        this.userService = userService;

    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody AccountDTO accountDTO) {

        try {


           String token = userService.login(accountDTO);
            return new TokenResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }



    }
    @PostMapping("register")
    public TokenResponse register(@RequestBody AccountDTO accountDTO) {


        try {

           String token = userService.register(accountDTO);
            System.out.println("тело и заголовок");
            return new TokenResponse(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }





}
