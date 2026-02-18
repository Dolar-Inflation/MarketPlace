package com.messenger.productlist.Entity.serservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", length = Integer.MAX_VALUE)
    private String amount;

    @Column(name = "acc_id")
    private Long accId;

    @Column(name = "product_id")
    private Long productId;

    @OneToMany
    private Set<Account> accounts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

}