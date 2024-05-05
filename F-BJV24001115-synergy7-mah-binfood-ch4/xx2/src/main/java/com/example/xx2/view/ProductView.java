package com.example.xx2.view;

import org.springframework.stereotype.Component;

@Component
public class ProductView {
    public void displayOptionMerchant() {
        System.out.println("""
                1. Tambah Menu
                2. Ubah Menu
                3. Hapus Menu
                4. Kelola Toko(Open/Close)
                
                => 
                """);
    }
}
