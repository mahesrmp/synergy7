package com.example.xx2.service;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.repository.OrderDetailRepository;
import com.example.xx2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrder(Orders order) {
        if (order.getOrder_time() == null) {
            order.setOrder_time(LocalDateTime.now());
        }
        orderRepository.save(order);
    }

    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
