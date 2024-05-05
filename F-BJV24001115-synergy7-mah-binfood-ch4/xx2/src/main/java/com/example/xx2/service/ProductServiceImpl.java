package com.example.xx2.service;

import com.example.xx2.model.Product;
import com.example.xx2.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;



    @Override
    public void createProduct(Integer price, String productName, UUID merchantId) {
        productRepository.callCreateProductProcedure(UUID.randomUUID(), price, productName, merchantId);
    }

    @Override
    public void updateProduct(Integer price, String productName, UUID productId) {
        productRepository.callUpdateProductProcedure(price, productName, productId);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        try {
            productRepository.callDeleteProductProcedure(productId);
            return true;
        } catch (Exception e) {
            log.error("Gagal menghapus produk dengan ID: {}", productId, e);
            return false;
        }
    }

    @Override
    public List<Product> getMenuList(UUID pilihanKedai) {
        return productRepository.findAllByMerchantId(pilihanKedai);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
