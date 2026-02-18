package com.messenger.productlist.Services;

import com.messenger.productlist.DTO.AccountDTO;
import com.messenger.productlist.DTO.ProductDTO;
import com.messenger.productlist.Entity.serservice.Account;
import com.messenger.productlist.Entity.serservice.Product;
import com.messenger.productlist.Reposytories.ProductsRepository;
import org.apache.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListService {

    private final EntityToDto entityToDto;
    private final ProductsRepository productsRepository;
    private final JwtService jwtService;


    public ProductListService(EntityToDto entityToDto, ProductsRepository productsRepository, JwtService jwtService) {
        this.entityToDto = entityToDto;
        this.productsRepository = productsRepository;
        this.jwtService = jwtService;
    }


   public List<ProductDTO> getAllProducts() {

       return  productsRepository.findAll().stream()
               .map(entityToDto::toDto)
               .collect(Collectors.toList());
    }


    public ResponseEntity<ProductDTO> CreateProduct(ProductDTO productDTO) {
    if (productDTO != null) {
        Product product = new Product();
        productsRepository.save(entityToDto.toEntity(productDTO));
        return ResponseEntity.ok(productDTO);
    }
    else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    public void OrderProduct(ProductDTO productDTO,AccountDTO accountDTO,String token) throws NoSuchAlgorithmException {
        jwtService.validateToken(token,accountDTO);
        Account account = new Account();
        account.setAccountname(jwtService.extractHeader(token,accountDTO).toString());
        Long productId = productsRepository.findIdByProductName(productDTO.getProductName());
        if (productId!=null) {
            Product product = productsRepository.findById(productId).get();




        }
        else throw new IllegalArgumentException("Product id is not exist");







    }

}
