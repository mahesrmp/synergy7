package org.example.service;

import org.example.model.entity.Product;

import java.util.Map;

public interface ProductService {
    Map<Long, Product> getMenuList();
    Product read(Long menu);
}
