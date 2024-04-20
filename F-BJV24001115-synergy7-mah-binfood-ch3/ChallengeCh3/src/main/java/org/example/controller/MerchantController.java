package org.example.controller;

import org.example.model.entity.Merchant;
import org.example.service.MerchantService;
import org.example.service.MerchantServiceImpl;
import org.example.view.MerchantView;

import java.util.Map;

public class MerchantController {
    MerchantService merchantService = new MerchantServiceImpl();

    public void menuKedaiCustomer() {
        MerchantView merchantView = new MerchantView();
        merchantView.displayKedai();
        displayKedais();
        merchantView.displayOption();
    }

    public void displayKedais() {
        Map<Long, Merchant> kedaiMap = merchantService.getMerchantList();
        MerchantView merchantView = new MerchantView();
        merchantView.displayMerchants(kedaiMap);
    }
}
