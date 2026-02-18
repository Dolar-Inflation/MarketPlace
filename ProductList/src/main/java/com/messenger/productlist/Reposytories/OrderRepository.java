package com.messenger.productlist.Reposytories;

import com.messenger.productlist.Entity.serservice.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
