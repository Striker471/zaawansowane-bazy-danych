package com.example.zbd.repository;

import com.example.zbd.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
