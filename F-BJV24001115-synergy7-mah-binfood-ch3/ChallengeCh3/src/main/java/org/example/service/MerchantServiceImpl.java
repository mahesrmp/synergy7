package org.example.service;

import org.example.Data;
import org.example.model.entity.Merchant;

import java.util.Map;

public class MerchantServiceImpl implements MerchantService{
    @Override
    public Map<Long, Merchant> getMerchantList() {
        return Data.merchantMap;
    }

}
