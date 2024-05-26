package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorWithBookAmountDTO {

    private String authorName;
    private Long bookAmount;
}
