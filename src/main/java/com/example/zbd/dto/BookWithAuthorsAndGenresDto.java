package com.example.zbd.dto;

import lombok.Data;

@Data
public class BookWithAuthorsAndGenresDto {

    private String title;
    private String authorName;
    private String genreName;
}
