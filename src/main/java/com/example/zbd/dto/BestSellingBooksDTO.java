package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestSellingBooksDTO {
    String title;
    Long numberOfOrders;
    Long numberOfCopy;
}
