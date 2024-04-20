package org.example.view;

import org.example.controller.MenuController;
import org.example.controller.PesananController;
import org.example.model.entity.OrderDetail;

import java.util.Map;
import java.util.Scanner;

public class PesananView {
    Scanner scanner = new Scanner(System.in);
    MenuController menuController = new MenuController();
    PesananController pesananController = new PesananController();

    int orderNumber = 1;
    public void displayTitlePesanan() {
        System.out.println("""
                ===================
                Berapa pesanan anda
                ===================
                """);
    }

    public void displayKonfirmasiPembayaran() {
        System.out.println("""
                =========================
                Konfirmasi dan Pembayaran
                =========================
                """);
    }

    public void displayOptionKonfirmasiPembayaran(){
        System.out.print("""
                1. Konfirmasi dan Bayar
                2. Kembali ke menu utama
                3. Keluar dari aplikasi

                """);
        int pilihanKonfirmasi = menuController.getInput();
        pesananController.konfirmasiPilihan(pilihanKonfirmasi);
    }

    public void displayPesananIsEmpty(){
        System.out.println("""
                =========================
                Minimal 1 jumlah pesanan!
                =========================
                """);
    }

    private void displayPesanan(OrderDetail pesananDetail){
        System.out.println(orderNumber++ +" | "+pesananDetail.getProduct().getProduct_name()+" | "+ pesananDetail.getQuantity()+" | "+pesananDetail.getProduct().getPrice());
    }

    public void displayPesanans(Map<Long, OrderDetail> pesananMap){
        //LAMBDA
        pesananMap.forEach((key, value) -> displayPesanan(value));
        System.out.println("----------------------------------------+");
        System.out.print("Total     ");
    }

}
