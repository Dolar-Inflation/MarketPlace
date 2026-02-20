package com.messenger.productlist.Controllers;

import com.messenger.productlist.DTO.*;
import com.messenger.productlist.Entity.serservice.Product;
import com.messenger.productlist.Reposytories.ProductsRepository;
import com.messenger.productlist.Services.EntityToDto;
import com.messenger.productlist.Services.ProductListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ProductListController {

    private final ProductListService productListService;
    private final RestTemplate restTemplate;
    private final ProductsRepository productsRepository;
    private final EntityToDto entityToDto;

    public ProductListController(ProductListService productListService, RestTemplate restTemplate, ProductsRepository productsRepository,  EntityToDto entityToDto) {
        this.productListService = productListService;
        this.restTemplate = restTemplate;
        this.productsRepository = productsRepository;
        this.entityToDto = entityToDto;

    }


    @PostMapping("/makeorder")
    public ResponseEntity<?> makeOrder(
            @RequestBody MakeOrderDTO request) throws NoSuchAlgorithmException {
        String token = request.getToken();
        AuthRequest authRequest = new AuthRequest(request.getAccountDTO(),token);
//        token = String.valueOf(restTemplate.postForObject("http://ser-service/auth/validate",authRequest, TokenResponse.class));

        TokenResponse response = restTemplate.postForObject( "http://ser-service/auth/validate", authRequest, TokenResponse.class );
        AccountDTO accountDTO = request.getAccountDTO();
        ProductDTO productDTO = request.getProductDTO();


        if (response == null) {
            log.warn("Cookie 'access' not found for user (products list)");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = response.getToken();

        productListService.OrderProduct(productDTO, accountDTO, username);

        return ResponseEntity.ok("Order created");
    }
    @GetMapping("productlist")
    public List<ProductDTO> getProductList() throws NoSuchAlgorithmException {
        return productsRepository.findAll()
                .stream()
                .map(entityToDto::toDto)
                .toList();
    }


}

