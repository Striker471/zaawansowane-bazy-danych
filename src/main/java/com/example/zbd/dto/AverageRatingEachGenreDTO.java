package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AverageRatingEachGenreDTO {

    private String genre;
    private Double rating;
}
