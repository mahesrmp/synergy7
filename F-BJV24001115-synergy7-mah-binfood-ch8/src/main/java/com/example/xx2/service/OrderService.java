package com.example.xx2.service;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.model.dto.OrderRequestDto;
import com.example.xx2.model.dto.OrderResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);
    List<OrderResponseDto> getAllOrders();
    List<OrderResponseDto> getOrdersByUserId(UUID userId);
    List<OrderResponseDto> getCompletedOrders();

//    List<OrderResponseDto> getOrdersByMerchantAndPeriod(UUID , LocalDateTime , LocalDateTime );
}
