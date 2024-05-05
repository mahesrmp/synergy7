package com.example.xx2.view;

import org.springframework.stereotype.Component;

@Component
public class UserView {
    public void displayAuthMenu() {
        System.out.print("""
                ==========================
                Selamat Datang di BinarFud
                ========================== 
                                
                Sudah memiliki akun?
                1 untuk Ya / 0 untuk Belum: 
                """);
    }
}
