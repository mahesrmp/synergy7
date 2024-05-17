package com.example.xx2.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponseDto {
    private UUID id;
    private LocalDateTime order_time;
    private String destination_address;
    private boolean completed;
    private UserResponseDto users;
    private List<OrderDetailResponseDto> orderDetails;
}
