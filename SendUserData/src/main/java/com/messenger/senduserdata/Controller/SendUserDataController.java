package com.messenger.senduserdata.Controller;

import com.messenger.senduserdata.Configuration.TokenResponse;
import com.messenger.senduserdata.DTO.AccountDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendUserDataController {

    private final RestTemplate restTemplate;

    public SendUserDataController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
@PostMapping("/send")
    public TokenResponse sendUserData(@RequestBody AccountDTO accountDTO) {




     return restTemplate.postForObject("http://ser-service/auth/register", accountDTO, TokenResponse.class);
    }

}
