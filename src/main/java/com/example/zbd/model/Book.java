package com.example.zbd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Books")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    private String title;
    private String publication_data;
    private double price;
    private int stock_quantity;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genres genres;

    @OneToMany(mappedBy = "book")
    public List<Review> reviews;

    @OneToMany(mappedBy = "book")
    public List<OrderDetails> orderDetails;
}
