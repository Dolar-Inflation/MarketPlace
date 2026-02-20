package com.messenger.productlist.Reposytories;

import com.messenger.productlist.Entity.serservice.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

   @Query("SELECT p.id FROM Product p WHERE p.productName = :name")
   Long findIdByProductName(@Param("name") String name);





}
