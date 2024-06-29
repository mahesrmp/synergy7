package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Product;
import com.example.xx2.model.dto.MerchantRequestOpenDto;
import com.example.xx2.model.dto.ProductCreateDto;
import com.example.xx2.model.dto.ProductDto;
import com.example.xx2.repository.MerchantRepository;
import com.example.xx2.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductDto create(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setProduct_name(productCreateDto.getProduct_name());
        product.setPrice(productCreateDto.getPrice());

        UUID merchantId = productCreateDto.getMerchant_id();
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
//        log.info(String.valueOf(merchantOptional));
        if (merchantOptional.isPresent()){
            product.setMerchant(merchantOptional.get());
        } else {
            throw new EntityNotFoundException("Merchant not found whit id: " + merchantId);
        }

        productRepository.save(product);

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product update(UUID idProduct, Product product) {
        product.setId(idProduct);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }
}
