package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{
    @Autowired
    private MerchantRepository merchantRepository;


    @Override
    public List<Merchant> getMerchantList() {
        return merchantRepository.getMerchantList();
    }

    @Override
    public Merchant create(Merchant merchant) {
        merchant = merchantRepository.save(merchant);
        return merchant;
    }

    public boolean getStatusToko(UUID merchantId) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        return merchantOptional.map(Merchant::isOpen).orElse(false);
    }

    public boolean tutupToko(UUID merchantId) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchant.setOpen(false);
            merchantRepository.save(merchant);
            return true;
        }
        return false;
    }

    public boolean bukaToko(UUID merchantId) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchant.setOpen(true);
            merchantRepository.save(merchant);
            return true;
        }
        return false;
    }

    @Override
    public UUID getIdMerchantByUserId(UUID userId) {
        Optional<Merchant> merchantOptional = merchantRepository.findByUsersId(userId);
        return merchantOptional.map(Merchant::getId).orElse(null);
    }

}
