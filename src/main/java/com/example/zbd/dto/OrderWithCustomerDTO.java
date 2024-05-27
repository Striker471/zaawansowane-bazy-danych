package com.example.zbd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderWithCustomerDTO {
    private String customerName;
    private String address;
    private String email;
    private String phoneNumber;
    private BigDecimal totalPrice;
}
