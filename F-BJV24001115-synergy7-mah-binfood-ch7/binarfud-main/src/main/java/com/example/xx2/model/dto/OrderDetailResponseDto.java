package com.example.xx2.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailResponseDto {
    private UUID id;
    private int quantity;
    private double total_price;
    private ProductResponseDto product;
}
