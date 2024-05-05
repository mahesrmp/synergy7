package com.example.xx2.controller;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Users;
import com.example.xx2.service.MerchantService;
import com.example.xx2.view.MerchantView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@Slf4j
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantView merchantView;

    private final Scanner scanner;
    private final OrderController orderController;

    @Autowired
    public MerchantController(MerchantService merchantService, MerchantView merchantView, Scanner scanner, OrderController orderController) {
        this.merchantService = merchantService;
        this.merchantView = merchantView;
        this.scanner = scanner;
        this.orderController = orderController;
    }

    private int pageNumber = 1;
    private int pageSize = 3;

    public void menuKedaiCustomer() {
        merchantView.displayKedai();
        displayKedais(pageNumber, pageSize);
        merchantView.displayOption();
        pageOption();
    }

    public void pageOption(){
        int pilihanKedai = scanner.nextInt();
        scanner.nextLine();
        if (pilihanKedai == 00) {
            nextPageMerchant();
        } else if (pilihanKedai == 99) {
            previousPageMerchant();
        } else {
            List<Merchant> merchantList = merchantService.getMerchantList();
            UUID selectedMerchantId = merchantList.get(pilihanKedai - 1).getId();
            orderController.mainMenuCustomer(selectedMerchantId);
        }
    }

    public void tambahMerchant(Users users){
        System.out.print("Nama Merchant: ");
        String nama_merchant = scanner.nextLine();
        System.out.print("Lokasi Merchant: ");
        String lokasi_merchant = scanner.nextLine();
        Merchant newMerchant = Merchant.builder()
                .merchant_name(nama_merchant)
                .merchant_location(lokasi_merchant)
                .open(true)
                .users(users)
                .build();
        merchantService.create(newMerchant);
    }

    public void displayKedais(int pageNumber, int pageSize) {
        List<Merchant> merchantList = merchantService.getMerchantList();
        merchantView.displayMerchants(merchantList, pageNumber, pageSize);
    }

    public void nextPageMerchant() {
        pageNumber++;
        menuKedaiCustomer();
    }

    public void previousPageMerchant() {
        if (pageNumber > 1) {
            pageNumber--;
            menuKedaiCustomer();
        } else {
            System.out.println("Anda sudah berada di halaman pertama.");
            menuKedaiCustomer();
        }
    }
}
