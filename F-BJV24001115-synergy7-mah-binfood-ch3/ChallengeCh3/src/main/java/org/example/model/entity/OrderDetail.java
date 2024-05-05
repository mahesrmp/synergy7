package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderDetail {
    private Long id;
    private Long order_id;
    private Long procuct_id;
    private int quantity;
    private int total_price;
    private Order order;
    private Product product;

    public OrderDetail(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDetail(Long id, Product product, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }
}
