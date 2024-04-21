package com.example.zbd.repository;

import com.example.zbd.dto.AverageRatingEachGenreDTO;
import com.example.zbd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


}
