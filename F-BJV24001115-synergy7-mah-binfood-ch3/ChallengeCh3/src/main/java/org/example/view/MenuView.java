package org.example.view;

import org.example.controller.PesananController;
import org.example.model.entity.Product;

import java.util.Map;
import java.util.Scanner;

public class MenuView {
    Scanner scanner = new Scanner(System.in);
    PesananController pesananController = new PesananController();
    public void displayTitle(){
        System.out.print("""
                Silahkan pilih makanan:
                """);
    }

    public void displayOption(){
        System.out.println("""
                99. Pesan dan Bayar
                0. Keluar Aplikasi
                """);
        int pilihanMenu = scanner.nextInt();
        pesananController.menuPilihan(pilihanMenu);
    }


    private void displayMenu(Product menu){
        System.out.println(menu.getId()+" | "+menu.getProduct_name()+" | "+menu.getPrice());
    }

    public void displayMenus(Map<Long, Product> menuMap){
        //LAMBDA
        menuMap.forEach((key, value) -> displayMenu(value));
    }
}
