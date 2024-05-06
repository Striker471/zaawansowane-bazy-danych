package com.example.zbd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int id;

    private int quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;



}

