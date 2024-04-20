package org.example.service;

import org.example.Data;
import org.example.model.entity.Product;

import java.util.Map;

public class ProductServiceImpl implements ProductService {
    @Override
    public Map<Long, Product> getMenuList() {
        return Data.menuMap;
    }

    @Override
    public Product read(Long menu) {
        return Data.menuMap.get(menu);
    }
}
