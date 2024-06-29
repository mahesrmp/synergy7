package com.example.xx2.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailDto {
    private UUID product_id;
    private int quantity;
}
