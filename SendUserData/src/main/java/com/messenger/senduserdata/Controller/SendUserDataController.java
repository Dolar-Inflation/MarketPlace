package com.messenger.senduserdata.Controller;

import com.messenger.senduserdata.Configuration.TokenResponse;
import com.messenger.senduserdata.DTO.AccountDTO;
import com.messenger.senduserdata.DTO.AuthRequest;
import com.messenger.senduserdata.DTO.MakeOrderDTO;
import com.messenger.senduserdata.DTO.ProductDTO;
import com.messenger.senduserdata.Services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    String token = jwtService.generateToken(accountDTO);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setBearerAuth(token);

//    tokenResponse.setToken(jwtService.generateToken(accountDTO));
//    AuthRequest authRequest = new AuthRequest(accountDTO,token);

//    HttpEntity<AuthRequest> entity = new HttpEntity<>(authRequest, headers);

    TokenResponse tokenResponse = restTemplate.postForObject("http://ser-service/auth/register", accountDTO, TokenResponse.class);

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
//        String token = jwtService.generateToken(accountDTO);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//        AuthRequest authRequest = new AuthRequest(accountDTO,token);
//        HttpEntity<AuthRequest> entity = new HttpEntity<>(authRequest, headers);

        TokenResponse tokenResponse =restTemplate.postForObject("http://ser-service/auth/login", accountDTO, TokenResponse.class);

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

    @PostMapping("/send/getapitoken")
    public ResponseEntity<TokenResponse> sendUserDataGetToken(
                                                              @CookieValue(name = "access", required = false) String token ) {
        if (token == null) {
            log.warn("Cookie 'access' not found for user ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        tokenResponse = restTemplate.postForObject("http://ser-service/auth/getapi",tokenResponse, TokenResponse.class);
        return ResponseEntity.ok(tokenResponse);

    }
    //TODO в токене хранится шифровонный логин
    // отправляем его из куки на стороне он проверяется валидириуется если всё ок приходит в
    // нужный сервис который расшифровывает логин и по логину добавляет
    // связб табл пользователя и заказы
    @PostMapping("/makeorder")
    public ResponseEntity<?> sendUserDataMakeOrder(@RequestBody MakeOrderDTO makeOrderDTO,@CookieValue(name = "access", required = false) String token) {
        if (token == null) {
            log.warn("Cookie 'access' not found for user (products list) ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else {




            makeOrderDTO.setToken(token);

            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
//            tokenResponse = restTemplate.postForObject("http://productlist/makeorder",makeOrderDTO, TokenResponse.class);
            makeOrderDTO.setToken(token);

            restTemplate.postForObject(
                    "http://PRODUCTLIST/makeorder",
                    makeOrderDTO,
                    String.class
            );


            return ResponseEntity.ok(tokenResponse);
        }

    }
    @PostMapping("productlist")
    public List<ProductDTO> getProductList(){

        return restTemplate.getForObject("http://PRODUCTLIST/productlist", List.class);

    }
}
