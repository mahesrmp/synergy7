package com.example.xx2.repository;

import com.example.xx2.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    List<Merchant> findByOpen(Boolean open);

    @Query(value = "SELECT * FROM merchant where open = true", nativeQuery = true)
    List<Merchant> getMerchantList();

    Merchant getMerchantById(Merchant merchantId);

    Optional<Merchant> findByUsersId(UUID userId);
}
