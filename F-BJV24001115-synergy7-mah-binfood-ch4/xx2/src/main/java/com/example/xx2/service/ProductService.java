package com.example.xx2.service;

import com.example.xx2.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getMenuList(UUID pilihanKedai);
    void createProduct(Integer price, String productName, UUID merchantId);
    void updateProduct(Integer price, String productName, UUID productId);
    boolean deleteProduct(UUID productId);
    Product getProductById(UUID productId);

}
