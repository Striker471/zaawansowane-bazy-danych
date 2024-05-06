package com.example.zbd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int id;

    private int rating;
    private String comment;

    @Column(name = "review_date")
    private Date reviewDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}