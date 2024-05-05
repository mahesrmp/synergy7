package org.example.controller;

import org.example.Data;
import org.example.model.entity.Merchant;
import org.example.model.entity.OrderDetail;
import org.example.model.entity.Product;
import org.example.model.entity.Order;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;
import org.example.view.PesananView;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.example.controller.MenuController.selectedMerchantId;

public class PesananController {
    Scanner scanner = new Scanner(System.in);
    MenuController menuController = new MenuController();
    OrderService orderService = new OrderServiceImpl();
    Map<Long, OrderDetail> pesananDetailMap = orderService.getPesananList();
    int orderNumber = 1;
    public void menuPilihan(int pilihanMenu) {
        try{
            if (isValidMenuOption(pilihanMenu)) {
                if (pilihanMenu >= 1 && pilihanMenu <= 5) {
                    PesananView pesananView = new PesananView();
                    pesananView.displayTitlePesanan();
                    Product menu = menuController.menuService.read((long) pilihanMenu);
                    pesananJumlah(menu, pilihanMenu);

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
        }
    }

    private boolean isValidMenuOption(int option) {
        return option >= 1 && option <= 5 || option == 99 || option == 0;
    }

    public void displayPesanans() {
        PesananView pesananView = new PesananView();
        if (pesananDetailMap.isEmpty()){
            pesananView.displayPesananIsEmpty();
            menuController.mainMenuCustomer(selectedMerchantId);
        }else {
            pesananView.displayKonfirmasiPembayaran();
            pesananView.displayPesanans(Data.pesananDetailMap);
            System.out.println(orderService.totalPembelian()+"        "+orderService.totalHarga());
            pesananView.displayOptionKonfirmasiPembayaran();
        }
    }

    public void konfirmasiPilihan(int pilihanKonfirmasi) {
        do {
            if (pilihanKonfirmasi == 1) {
                cetakStruk();
                break;
            } else if (pilihanKonfirmasi == 2) {
                menuController.mainMenuCustomer(selectedMerchantId);
                break;
            } else if (pilihanKonfirmasi == 3) {
                System.exit(0);
            } else {
                System.out.println("Inputan tidak valid. Harap masukkan pilihan yang sesuai.");
                pilihanKonfirmasi = menuController.getInput();
            }
        }while(true);

    }


    public void pesananJumlah(Product menu, int pilihanMenu){
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        try {
            //CONSUMER
            Consumer<String> consumer = t-> System.out.println(t);
            consumer.accept("Masukkan jumlah pesanan: ");
            int jumlahPesanan = Integer.parseInt(scanner.nextLine());
            if (jumlahPesanan == 0) {
                menuController.mainMenuCustomer(selectedMerchantId);
            } else if (jumlahPesanan < 0) {
                System.out.println("Maaf, jumlah pesanan harus lebih besar dari nol.");
                menuPilihan(pilihanMenu);
            } else {
                order.setOrder_time(LocalDateTime.now());
                orderDetail.setOrder(order);
                orderDetail.setProduct(menu);
                orderDetail.setQuantity(jumlahPesanan);
                order.getOrderDetails().add(orderDetail);
                orderService.createPesanan(order);

                System.out.println("Pesanan Anda telah ditambahkan.");
                menuController.mainMenuCustomer(selectedMerchantId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Maaf, harap masukkan angka yang valid.");
            menuPilihan(pilihanMenu);
        }
    }

    public void cetakStruk() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timeStamp = LocalDateTime.now().format(formatter);
            Merchant selectedMerchant = Data.merchantMap.get(selectedMerchantId);
            String namaFile = "struk_belanja_"+ selectedMerchant.getMerchant_name() + "_" + timeStamp +  ".txt";

            FileWriter writer = new FileWriter(namaFile);

            writer.write("==================== Struk Belanja ====================\n");
            for (OrderDetail orderDetail : pesananDetailMap.values()) {
                writer.write(orderNumber++ + " | " + orderDetail.getProduct().getProduct_name() + " | " + orderDetail.getQuantity() + " | " + orderDetail.getProduct().getPrice() + "\n");
            }
            writer.write("Total Harga:      " +orderService.totalPembelian()+"    "+ orderService.totalHarga() + "\n");


            writer.close();
            System.out.println("Struk belanja telah berhasil dicetak ke file " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mencetak struk belanja.");
            e.printStackTrace();
        }
    }
}
