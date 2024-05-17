package com.example.xx2.controller;

import com.example.xx2.model.Product;
import com.example.xx2.model.dto.ProductCreateDto;
import com.example.xx2.model.dto.ProductDto;
import com.example.xx2.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {
    @Autowired
    ModelMapper modelMapper;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public List<ProductDto> getProductAll() {
//        return productService.getAll().stream()
//                .map(product -> modelMapper.map(product, ProductDto.class))
//                .toList();
//    }

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id){
//        Optional<Product> product = productRepository.findById(id);
        Optional<Product> product = Optional.ofNullable(productService.getProductById(id));
        if (product.isPresent()){
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(product.get(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("other/{id}")
    public ResponseEntity<ProductDto> getProductByIdOther(@PathVariable("id") UUID id){
//        Optional<Product> product = productRepository.findById(id);
        Optional<Product> productOptional = Optional.ofNullable(productService.getProductById(id));
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            ProductDto productDto = new ProductDto(); // Anda perlu membuat instance ProductDto dan mengisinya dengan data dari Product
            // Contoh pengisian data dari Product ke ProductDto
            productDto.setId(product.getId());
            productDto.setProduct_name(product.getProduct_name());
            productDto.setPrice(product.getPrice());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody ProductCreateDto productCreateDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("product", productService.create(productCreateDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public Product add(@PathVariable("id") UUID idProduct, @RequestBody Product product){
        return productService.update(idProduct, product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") UUID id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
