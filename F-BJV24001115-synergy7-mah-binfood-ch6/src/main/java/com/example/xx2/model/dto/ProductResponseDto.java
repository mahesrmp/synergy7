package com.example.xx2.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponseDto {
    private UUID id;
    private String product_name;
    private double price;
}
