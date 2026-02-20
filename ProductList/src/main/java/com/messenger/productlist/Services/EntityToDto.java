package com.messenger.productlist.Services;

import com.messenger.productlist.DTO.ProductDTO;
import com.messenger.productlist.Entity.serservice.Product;
import org.springframework.stereotype.Component;

@Component
public class EntityToDto {

    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        return product;
    }

}
