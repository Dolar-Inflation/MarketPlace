package com.messenger.productlist.Controllers;

import com.messenger.productlist.DTO.*;
import com.messenger.productlist.Services.ProductListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
@Slf4j
@RestController
public class ProductListController {

    private final ProductListService productListService;
    private final RestTemplate restTemplate;

    public ProductListController(ProductListService productListService, RestTemplate restTemplate) {
        this.productListService = productListService;
        this.restTemplate = restTemplate;
    }


    @PostMapping("/makeorder")
    public ResponseEntity<?> makeOrder(
            @RequestBody MakeOrderDTO request) throws NoSuchAlgorithmException {
        String token = request.getToken();
        AuthRequest authRequest = new AuthRequest(request.getAccountDTO(),token);
        token = String.valueOf(restTemplate.postForObject("http://ser-service/auth/validate",authRequest, TokenResponse.class));
        AccountDTO accountDTO = request.getAccountDTO();
        ProductDTO productDTO = request.getProductDTO();


        if (token == null) {
            log.warn("Cookie 'access' not found for user (products list)");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        productListService.OrderProduct(productDTO, accountDTO, token);

        return ResponseEntity.ok("Order created");
    }
}

