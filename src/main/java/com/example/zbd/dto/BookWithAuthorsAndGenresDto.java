package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookWithAuthorsAndGenresDto {

    private String title;
    private String authorName;
    private String genreName;
}
