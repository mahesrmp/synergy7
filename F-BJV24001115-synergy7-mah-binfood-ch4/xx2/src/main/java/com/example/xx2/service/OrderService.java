package com.example.xx2.service;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;

public interface OrderService {
    void createOrder(Orders order);
    void createOrderDetail(OrderDetail orderDetail);
}
