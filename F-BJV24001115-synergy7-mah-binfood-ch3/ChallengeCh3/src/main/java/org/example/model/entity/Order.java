package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Order {
    private Long id;
    private LocalDateTime order_time;
    private String destination_address;
    private Long user_id;
    private boolean completed;
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
