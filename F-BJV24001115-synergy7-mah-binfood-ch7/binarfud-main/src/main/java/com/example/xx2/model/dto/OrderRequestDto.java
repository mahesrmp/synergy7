package com.example.xx2.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderRequestDto {
    private UUID user_id;
    private String destination_address;
    private List<OrderDetailDto> orderDetails;
}
