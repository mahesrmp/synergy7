package com.example.xx2.service;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.model.Product;
import com.example.xx2.model.Users;
import com.example.xx2.model.dto.*;
import com.example.xx2.repository.OrderDetailRepository;
import com.example.xx2.repository.OrderRepository;
import com.example.xx2.repository.ProductRepository;
import com.example.xx2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDTO) {
        UUID userId = orderRequestDTO.getUser_id();
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setOrder_time(LocalDateTime.now());
        order.setDestination_address(orderRequestDTO.getDestination_address());
        order.setUsers(user);
        order.setCompleted(true);

        List<OrderDetail> orderDetails = orderRequestDTO.getOrderDetails().stream().map(dto -> {
            Product product = productRepository.findById(dto.getProduct_id()).orElseThrow(() -> new RuntimeException("Product not found"));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(dto.getQuantity());
            orderDetail.setTotal_price(product.getPrice() * dto.getQuantity());
            orderDetail.setOrders(order);
            orderDetail.setProduct(product);
            return orderDetail;
        }).collect(Collectors.toList());

        order.setOrderDetails(orderDetails);

        orderRepository.save(order);
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<OrderResponseDto> getOrdersByUserId(UUID userId) {
        return orderRepository.findByUsersId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<OrderResponseDto> getCompletedOrders() {
        return orderRepository.findByCompleted(true).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

//    @Override
//    public List<OrderResponseDto> getOrdersByMerchantAndPeriod(UUID merchantId, LocalDateTime startDate, LocalDateTime endDate) {
//        // Implementasi logika untuk mendapatkan pesanan berdasarkan merchant dan periode waktu
//        // Ini mungkin melibatkan perubahan pada OrderRepository dan penambahan query khusus
//        return orderRepository.findByMerchantAndPeriod(merchantId, startDate, endDate)
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    private OrderResponseDto convertToDTO(Orders order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrder_time(order.getOrder_time());
        dto.setDestination_address(order.getDestination_address());
        dto.setCompleted(order.isCompleted());
        dto.setUsers(convertToDTO(order.getUsers()));
        dto.setOrderDetails(order.getOrderDetails().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return dto;
    }

    private OrderDetailResponseDto convertToDTO(OrderDetail orderDetail) {
        OrderDetailResponseDto dto = new OrderDetailResponseDto();
        dto.setId(orderDetail.getId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setTotal_price(orderDetail.getTotal_price());
        dto.setProduct(convertToDTO(orderDetail.getProduct()));
        return dto;
    }

    private ProductResponseDto convertToDTO(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setProduct_name(product.getProduct_name());
        dto.setPrice(product.getPrice());
        return dto;
    }

    private UserResponseDto convertToDTO(Users user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
