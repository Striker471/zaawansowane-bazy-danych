package com.example.zbd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;

    private String genreName;

    @OneToMany(mappedBy = "genres")
    private List<Book> books;
}
