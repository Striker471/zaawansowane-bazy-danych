package com.example.zbd.repository;

import com.example.zbd.dto.AverageRatingEachGenreDTO;
import com.example.zbd.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("select new com.example.zbd.dto.AverageRatingEachGenreDTO(g.genreName, avg(r.rating)) " +
            "from Genre g" +
            " join g.books b" +
            " join b.reviews r" +
            " group by g.genreName" +
            " order by avg(r.rating) desc")
    List<AverageRatingEachGenreDTO> findAverageRatingEachGenre();
}
