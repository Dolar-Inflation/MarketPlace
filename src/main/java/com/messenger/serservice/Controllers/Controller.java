package com.messenger.serservice.Controllers;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody AccountDTO user) {

        return   userService.login(user);


    }
    @PostMapping("register")
    public TokenResponse register(@RequestBody AccountDTO user) {
        return  userService.register(user);

    }





}
