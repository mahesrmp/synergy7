package com.example.xx2.view;

import com.example.xx2.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderView {
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
