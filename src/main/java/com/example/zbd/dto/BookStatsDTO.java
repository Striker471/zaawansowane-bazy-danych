package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookStatsDTO {
    String title;
    Long uniqueClients;
    Long orderBooks;
    Double rating;
}
