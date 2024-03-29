package org.example.view;

import org.example.Data;
import org.example.controller.MenuController;
import org.example.controller.PesananController;
import org.example.model.entity.Menu;
import org.example.model.entity.Pesanan;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PesananView {
    Scanner scanner = new Scanner(System.in);
    MenuController menuController = new MenuController();
    PesananController pesananController = new PesananController();
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

    private void displayPesanan(Pesanan pesanan){
        System.out.println(pesanan.getId()+" | "+pesanan.getMenu().getNamaMenu()+" | "+ pesanan.getJumlahPesanan()+" | "+pesanan.getMenu().getHargaMenu());
    }

    public void displayPesanans(Map<Long, Pesanan> pesananMap){
        Set<Long> setId = Data.pesananMap.keySet();
        for (Long key : setId) {
            displayPesanan(Data.pesananMap.get(key));
        }
        System.out.println("----------------------------------------+");
        System.out.print("Total     ");
    }

    public void pesananJumlah(Menu menu, int pilihanMenu){
        Pesanan pesanan = new Pesanan();
        try {
            System.out.print("Masukkan jumlah pesanan: ");
            int jumlahPesanan = Integer.parseInt(scanner.nextLine());
            if (jumlahPesanan == 0) {
                menuController.mainMenu();
            } else if (jumlahPesanan < 0) {
                System.out.println("Maaf, jumlah pesanan harus lebih besar dari nol.");
                pesananController.menuPilihan(pilihanMenu);
            } else {
                pesanan.setMenu(menu);
                pesanan.setJumlahPesanan(jumlahPesanan);
                pesanan.setCreatedTime(LocalDateTime.now());
                pesananController.addPesanan(pesanan);
                System.out.println("Pesanan Anda telah ditambahkan.");
                menuController.mainMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("Maaf, harap masukkan angka yang valid.");
            pesananController.menuPilihan(pilihanMenu);
        }
    }
}
