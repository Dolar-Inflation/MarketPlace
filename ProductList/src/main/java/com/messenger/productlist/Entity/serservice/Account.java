package com.messenger.productlist.Entity.serservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Entity
@Table(name = "account", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "account_unique", columnNames = {"accountname"})
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @jakarta.validation.constraints.NotNull
    @Column(name = "accountname", nullable = false, length = Integer.MAX_VALUE)
    private String accountname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acc_order_id")
    private Order accOrder;

}