package org.example.controller;

import org.example.model.entity.Menu;
import org.example.model.entity.Pesanan;
import org.example.service.PesananService;
import org.example.service.PesananServiceImpl;
import org.example.view.PesananView;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Map;

public class PesananController {
    MenuController menuController = new MenuController();
    PesananService pesananService = new PesananServiceImpl();
    Map<Long, Pesanan> pesananMap = pesananService.getPesananList();

    public void addPesanan(Pesanan pesanan) {
        pesananService.createPesanan(pesanan);
    }

    public void menuPilihan(int pilihanMenu) {
        try{
            if (isValidMenuOption(pilihanMenu)) {
                if (pilihanMenu >= 1 && pilihanMenu <= 5) {
                    PesananView pesananView = new PesananView();
                    pesananView.displayTitlePesanan();
                    Menu menu = menuController.menuService.read((long) pilihanMenu);
                    pesananView.pesananJumlah(menu, pilihanMenu);

                } else if (pilihanMenu == 99) {
                    displayPesanans();
                } else if (pilihanMenu == 0) {
                    System.exit(0);
                }
            }else {
                throw new InputMismatchException("Inputan tidak valid. Harap masukkan pilihan yang sesuai.");
            }
        }catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            menuController.mainMenu();
        }
    }

    private boolean isValidMenuOption(int option) {
        return option >= 1 && option <= 5 || option == 99 || option == 0;
    }

    public void displayPesanans() {
        PesananView pesananView = new PesananView();
        if (pesananMap.isEmpty()){
            pesananView.displayPesananIsEmpty();
            menuController.mainMenu();
        }else {
            pesananView.displayKonfirmasiPembayaran();
            pesananView.displayPesanans(pesananMap);
            System.out.println(pesananService.totalPembelian()+"        "+pesananService.totalHarga());
            pesananView.displayOptionKonfirmasiPembayaran();
        }
    }

    public void konfirmasiPilihan(int pilihanKonfirmasi) {
        if (pilihanKonfirmasi == 1) {
            cetakStruk();

        } else if (pilihanKonfirmasi == 2) {
            menuController.mainMenu();
        } else if (pilihanKonfirmasi == 0) {
            System.exit(0);
        }
    }

    public void cetakStruk() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timeStamp = LocalDateTime.now().format(formatter);

            String namaFile = "struk_belanja_BinarFud" + timeStamp + ".txt";

            FileWriter writer = new FileWriter(namaFile);

            writer.write("==================== Struk Belanja ====================\n");
            for (Pesanan pesanan : pesananMap.values()) {
                writer.write(pesanan.getId() + " | " + pesanan.getMenu().getNamaMenu() + " | " + pesanan.getJumlahPesanan() + " | " + pesanan.getMenu().getHargaMenu() + "\n");
            }
            writer.write("Total Harga:      " +pesananService.totalPembelian()+"    "+ pesananService.totalHarga() + "\n");


            writer.close();
            System.out.println("Struk belanja telah berhasil dicetak ke file " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mencetak struk belanja.");
            e.printStackTrace();
        }
    }
}
