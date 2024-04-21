package com.example.zbd.repository;

import com.example.zbd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    long countByCustomerId(Integer customerId);

}
