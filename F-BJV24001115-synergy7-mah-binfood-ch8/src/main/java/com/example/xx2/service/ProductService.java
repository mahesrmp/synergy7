package com.example.xx2.service;

import com.example.xx2.model.Product;
import com.example.xx2.model.dto.ProductCreateDto;
import com.example.xx2.model.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();
    Product getProductById(UUID productId);
    ProductDto create(ProductCreateDto productCreateDto);
    Product update(UUID idProduct, Product product);
    void deleteProduct(UUID productId);

    List<ProductDto> getAllProducts();
}
