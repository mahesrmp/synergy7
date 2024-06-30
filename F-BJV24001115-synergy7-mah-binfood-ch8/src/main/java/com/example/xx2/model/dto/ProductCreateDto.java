package com.example.xx2.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductCreateDto {
    private String product_name;
    private int price;
    private int stok;
    private UUID merchant_id;
}
