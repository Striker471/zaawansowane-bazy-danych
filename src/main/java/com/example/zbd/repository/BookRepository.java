package com.example.zbd.repository;

import com.example.zbd.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.genreName = :genreName")
    List<Book> findByGenreName(@Param("genreName") String genreName);


}
