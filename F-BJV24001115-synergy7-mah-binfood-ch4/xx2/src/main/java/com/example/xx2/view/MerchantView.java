package com.example.xx2.view;

import com.example.xx2.controller.MerchantController;
import com.example.xx2.controller.OrderController;
import com.example.xx2.model.Merchant;
import com.example.xx2.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class MerchantView {
    private final OrderController orderController;
    private final MerchantService merchantService;
    private final Scanner scanner;

    public static UUID selectedMerchantId;

    @Autowired
    public MerchantView(OrderController orderController, MerchantService merchantService, Scanner scanner) {
        this.orderController = orderController;
        this.merchantService = merchantService;
        this.scanner = scanner;
    }

    public void displayKedai(){
        System.out.print("""
                ==============
                Selamat Datang
                ==============

                Silahkan pilih Kedai:
                """);
    }

    public void displayOption(){
        System.out.println("""
                00. Next Page
                99. Previous Page
                Masukkan pilihan kedai
                =>
                """);
    }

    public void displayMerchants(List<Merchant> merchantList, int pageNumber, int pageSize) {
        int totalMerchants = merchantList.size();
        int totalPages = (int) Math.ceil((double) totalMerchants / pageSize);
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalMerchants);

        System.out.println("=== Halaman " + pageNumber + " dari " + totalPages + " ===");
        int[] counter = {startIndex + 1};
        merchantList.subList(startIndex, endIndex).forEach(merchant -> System.out.println((counter[0]++) +
                " | " + merchant.getMerchant_name() + " | " + merchant.getMerchant_location()));
    }
}
