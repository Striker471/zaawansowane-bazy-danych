package com.example.zbd.repository;

import com.example.zbd.dto.AuthorWithBookAmountDto;

import com.example.zbd.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select a.name, count(b) from Author a join a.books b group by a.name")
    List<AuthorWithBookAmountDto> getAuthorsWithBookAmount();
}
