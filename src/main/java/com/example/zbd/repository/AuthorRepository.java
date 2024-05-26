package com.example.zbd.repository;

import com.example.zbd.dto.AuthorWithBookAmountDTO;

import com.example.zbd.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select new com.example.zbd.dto.AuthorWithBookAmountDTO(a.name, count(b))" +
            " from Author a" +
            " join a.books b" +
            " group by a.name")
    List<AuthorWithBookAmountDTO> getAuthorsWithBookAmount();

    Optional<Author> findByName(String name);
}
