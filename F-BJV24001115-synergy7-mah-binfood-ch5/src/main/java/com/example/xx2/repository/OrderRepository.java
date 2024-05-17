package com.example.xx2.repository;

import com.example.xx2.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUsersId(UUID userId);

    List<Orders> findByCompleted(boolean completed);

    @Query("SELECT o FROM Orders o WHERE o.order_time BETWEEN :startDate AND :endDate")
    List<Orders> findByOrderTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
