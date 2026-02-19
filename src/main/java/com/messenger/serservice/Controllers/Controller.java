package com.messenger.serservice.Controllers;

import com.messenger.serservice.DTO.AccountDTO;
import com.messenger.serservice.DTO.AuthRequest;
import com.messenger.serservice.DTO.TokenResponse;
import com.messenger.serservice.Services.JwtService;
import com.messenger.serservice.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class Controller {

    private final UserService userService;
    private final JwtService jwtService;


    public Controller(UserService userService, JwtService jwtService) {
        this.userService = userService;

        this.jwtService = jwtService;
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
    @PostMapping("validate")
    public TokenResponse validate(@RequestBody AuthRequest request) throws NoSuchAlgorithmException {
        AccountDTO accountDTO = request.getAccount();
        String token = request.getToken();
       if (jwtService.validateToken(token, accountDTO)){

           accountDTO.setAccountName( jwtService.ExtractClaim(token,accountDTO));

           return new TokenResponse(accountDTO.getAccountName());
       }
        else {
        return null;
       }
    }





}
