package org.example.view;

import org.example.controller.MenuController;
import org.example.model.entity.Merchant;
import org.example.model.entity.Product;

import java.util.Map;
import java.util.Scanner;

public class MerchantView {
    Scanner scanner = new Scanner(System.in);
    MenuController menuController = new MenuController();
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
                Masukkan pilihan kedai
                => 
                """);
        int pilihanKedai = scanner.nextInt();
        menuController.mainMenuCustomer(Long.valueOf(pilihanKedai));
    }

    private void displayMerchant(Merchant merchant){
        if (merchant.isOpen() == true){
            System.out.println(merchant.getId()+" | "+merchant.getMerchant_name()+" | "+merchant.getMerchant_location());
        }
    }

    public void displayMerchants(Map<Long, Merchant> merchantMap){
        //LAMBDA
        merchantMap.forEach((key, value) -> displayMerchant(value));
    }
}
