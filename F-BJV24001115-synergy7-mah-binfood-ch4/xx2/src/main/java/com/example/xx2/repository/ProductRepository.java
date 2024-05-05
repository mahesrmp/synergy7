package com.example.xx2.repository;

import com.example.xx2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllByMerchantId(UUID merchantId);

    @Procedure(procedureName = "CREATE_PRODUCT")
    void callCreateProductProcedure(UUID id, int price, String product_name, UUID merchant_Id);

    @Procedure(procedureName = "update_product")
    void callUpdateProductProcedure(Integer price, String productName, UUID productId);

    @Procedure(procedureName = "delete_product")
    void callDeleteProductProcedure(UUID productId);

}
