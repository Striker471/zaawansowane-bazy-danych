package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class NumberEachBookSoldDTO {

    private String title;
    private Long number;
}
