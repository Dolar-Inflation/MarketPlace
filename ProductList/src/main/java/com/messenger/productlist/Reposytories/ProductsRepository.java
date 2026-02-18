package com.messenger.productlist.Reposytories;

import com.messenger.productlist.Entity.serservice.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

   public long findIdByProductName(String name);



}
