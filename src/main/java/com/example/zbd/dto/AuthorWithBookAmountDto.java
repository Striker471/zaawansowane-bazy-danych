package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorWithBookAmountDto {

    private String authorName;
    private Long bookAmount;
}
