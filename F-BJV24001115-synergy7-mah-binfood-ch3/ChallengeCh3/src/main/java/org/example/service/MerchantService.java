package org.example.service;

import org.example.model.entity.Merchant;
import org.example.model.entity.Product;

import java.util.Map;

public interface MerchantService {
    Map<Long, Merchant> getMerchantList();
}
