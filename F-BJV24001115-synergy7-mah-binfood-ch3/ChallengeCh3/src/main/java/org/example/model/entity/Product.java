package org.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String product_name;
    private int price;
    private Long merchant_id;
    private Merchant merchant;

    public Product(String product_name, int price) {
        this.product_name = product_name;
        this.price = price;
    }

    public Product(Long id, String product_name, int price) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
    }
}
