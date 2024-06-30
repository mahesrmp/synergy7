package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Product;
import com.example.xx2.model.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService{
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void send(List<Product> productPromoList) {
//        productPromoList.stream()
//                .forEach(product -> System.out.println("Nama Merchant: "+ product.getMerchant().getMerchant_name() + "\nProduct: "+ product.getProduct_name()));

        Map<Merchant, List<Product>> productsByMerchant = productPromoList.stream()
                .collect(Collectors.groupingBy(Product::getMerchant));

        productsByMerchant.forEach((merchant, products) -> {
            System.out.println("Nama Merchant: " + merchant.getMerchant_name());
            products.forEach(product -> System.out.println("Product: " + product.getProduct_name()));
        });
    }
}
