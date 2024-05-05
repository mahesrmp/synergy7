package com.example.xx2.service;

import com.example.xx2.model.Merchant;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> getMerchantList();

    Merchant create(Merchant merchant);
    boolean getStatusToko(UUID merchantId);
    boolean tutupToko(UUID merchantId);
    boolean bukaToko(UUID merchantId);

    UUID getIdMerchantByUserId(UUID userId);
}
