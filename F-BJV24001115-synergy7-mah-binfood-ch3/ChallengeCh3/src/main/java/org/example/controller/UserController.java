package org.example.controller;

import org.example.view.UserView;

import java.util.InputMismatchException;

public class UserController {
    UserView userView = new UserView();

    public void authMenu(){
        userView.displayAuth();
    }

    public void authPilihan(int pilihanAuth){
        try {
            if (pilihanAuth == 1){
                userView.formLogin();
            } else if (pilihanAuth == 0) {
                userView.formRegister();
            }else {
                System.out.println("Inputan tidak valid. Harap Masukkan 1 untuk Ya sudah punya akun atau 0 untuk Belum memiliki akun");
            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            authMenu();
        }
    }
}
