package com.example.neobis.repository;

import com.example.neobis.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {


}
