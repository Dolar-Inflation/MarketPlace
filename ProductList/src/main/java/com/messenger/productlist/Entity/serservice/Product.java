package com.messenger.productlist.Entity.serservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "product_name", nullable = false, length = Integer.MAX_VALUE)
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "order_id")
    private Long orderId;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

}