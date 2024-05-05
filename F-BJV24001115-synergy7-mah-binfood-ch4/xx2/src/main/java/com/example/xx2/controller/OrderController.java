package com.example.xx2.controller;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.model.Product;
import com.example.xx2.service.OrderService;
import com.example.xx2.service.ProductService;
import com.example.xx2.view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

import static com.example.xx2.service.UserServiceImpl.currentUserId;
import static com.example.xx2.view.MerchantView.selectedMerchantId;

@Component
public class OrderController {
    private List<Orders> ordersList = new ArrayList<>();
    private List<OrderDetail> orderDetailList = new ArrayList<>();
    private final OrderView orderView;
    private final OrderService orderService;
    private final ProductController productController;
    private final ProductService productService;
    private final Scanner scanner;

    @Autowired
    public OrderController(OrderView orderView, OrderService orderService, ProductController productController, ProductService productService, Scanner scanner) {
        this.orderView = orderView;
        this.orderService = orderService;
        this.productController = productController;
        this.productService = productService;
        this.scanner = scanner;
    }

    public void menuPilihan() {
        try {
            System.out.println("=> ");
            int pilihanMenu = scanner.nextInt();
            List<Product> menuList = productService.getMenuList(selectedMerchantId);

            if (pilihanMenu >= 1 && pilihanMenu <= menuList.size()) {
                UUID selectedMenuId = menuList.get(pilihanMenu - 1).getId();
                Product selectedProduct = productService.getProductById(selectedMenuId);
                orderView.displayTitlePesanan();
                System.out.println(selectedProduct.getProduct_name() + selectedProduct.getPrice());
                pesananJumlah(selectedProduct, selectedMenuId);

            } else if (pilihanMenu == 99) {
                displayPesanans();
            } else if (pilihanMenu == 00) {
                ;
            } else if (pilihanMenu == 0) {
                System.exit(0);
            } else {
                throw new InputMismatchException("Inputan tidak valid. Harap masukkan pilihan yang sesuai.");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayPesanans() {
        if (ordersList.isEmpty()) {
            orderView.displayPesananIsEmpty();
            mainMenuCustomer(selectedMerchantId);
        } else {
            double totalHarga = 0.0;
            orderView.displayKonfirmasiPembayaran();
            for (Orders order : ordersList) {
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    System.out.println(orderDetail.getProduct().getProduct_name() + "    " + orderDetail.getQuantity() + "    " + (orderDetail.getQuantity() * orderDetail.getProduct().getPrice()));
                    totalHarga += (orderDetail.getQuantity() * orderDetail.getProduct().getPrice());
                }
            }
            System.out.println("------------------------------------------------------------------");
            System.out.println("Total harga: " + totalHarga);
            inputOptionKonfirmasiPembayaran();
        }
    }

    public void konfirmasiPilihan(int pilihanKonfirmasi) {
        do {
            if (pilihanKonfirmasi == 1) {
                konfirmasiPembayaran();
                break;
            } else if (pilihanKonfirmasi == 2) {
                mainMenuCustomer(selectedMerchantId);
                break;
            } else if (pilihanKonfirmasi == 3) {
                System.exit(0);
            } else {
                System.out.println("Inputan tidak valid. Harap masukkan pilihan yang sesuai.");
                inputOptionKonfirmasiPembayaran();
            }
        } while (true);

    }

    public void konfirmasiPembayaran() {
        try {
            System.out.println("Masukkan alamat tujuan pengiriman:");
            String destinationAddress = scanner.nextLine().trim();

            if (destinationAddress.isEmpty()) {
                System.out.println("Alamat tujuan pengiriman tidak boleh kosong.");
                konfirmasiPembayaran();
                return;
            }

            System.out.println("Anda yakin ingin melanjutkan pembayaran? (Ya/Tidak)");
            String konfirmasi = scanner.next();

            if (konfirmasi.equalsIgnoreCase("Ya")) {
                Orders order = new Orders();
                order.setOrder_time(LocalDateTime.now());
                order.setDestination_address(destinationAddress);
                order.setCompleted(true);
                order.setUsers(currentUserId);
                orderService.createOrder(order);
                double totalHarga = 0.0;

                for (Orders existingOrder  : ordersList) {
                    for (OrderDetail orderDetail : existingOrder.getOrderDetails()) {
                        Product product = orderDetail.getProduct();
                        int quantity = orderDetail.getQuantity();
                        double hargaSatuan = product.getPrice();
                        double totalHargaDetail = hargaSatuan * quantity;

                         totalHarga += totalHargaDetail;
                         orderDetail.setQuantity(quantity);
                         orderDetail.setTotal_price(totalHarga);
                         orderDetail.setOrders(order);
                         orderDetail.setProduct(product);
                        orderService.createOrderDetail(orderDetail);
                    }
                }

                ordersList.clear();
                orderDetailList.clear();

                cetakStruk();
                System.out.println("Terima kasih telah melakukan pembayaran.");
                mainMenuCustomer(selectedMerchantId);
            } else if (konfirmasi.equalsIgnoreCase("Tidak")) {
                inputOptionKonfirmasiPembayaran();
            } else {
                System.out.println("Masukkan tidak valid. Harap masukkan 'Ya' atau 'Tidak'.");
                konfirmasiPembayaran();
            }

        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid.");
            scanner.nextLine();
            konfirmasiPembayaran();
        }
    }


    public void pesananJumlah(Product menu, UUID pilihanMenu) {
        try {
            Orders orders = new Orders();
            orders.setOrderDetails(new ArrayList<>());
            Consumer<String> consumer = t -> System.out.println(t);
            consumer.accept("Masukkan jumlah pesanan: ");
            int jumlahPesanan = scanner.nextInt();
            if (jumlahPesanan == 0) {
                mainMenuCustomer(selectedMerchantId);
            } else if (jumlahPesanan < 0) {
                System.out.println("Maaf, jumlah pesanan harus lebih besar dari nol.");
                mainMenuCustomer(selectedMerchantId);
            } else {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(menu);
                orderDetail.setQuantity(jumlahPesanan);
                orders.getOrderDetails().add(orderDetail);
                ordersList.add(orders); // Menambahkan pesanan ke dalam List
                System.out.println("Pesanan Anda telah ditambahkan.");
                mainMenuCustomer(selectedMerchantId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Maaf, harap masukkan angka yang valid.");
            mainMenuCustomer(selectedMerchantId);
        }
    }

    public void cetakStruk() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timeStamp = LocalDateTime.now().format(formatter);
            String namaFile = "struk_belanja_" + "_" + timeStamp + ".txt";

            FileWriter writer = new FileWriter(namaFile);

            writer.write("==================== Struk Belanja ====================\n");
            writer.write("Nama Produk | Jumlah | Harga Satuan | Total Harga\n");
            double totalHarga = 0.0;

            for (Orders order : ordersList) {
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    Product product = orderDetail.getProduct();
                    int quantity = orderDetail.getQuantity();
                    double hargaSatuan = product.getPrice();
                    double totalHargaDetail = hargaSatuan * quantity;

                    writer.write(product.getProduct_name() + " | " + quantity + " | " + hargaSatuan + " | " + totalHargaDetail + "\n");

                    totalHarga += totalHargaDetail;
                }
            }

            writer.write("\nTotal Harga: " + totalHarga + "\n");

            writer.close();
            System.out.println("Struk belanja telah berhasil dicetak ke file " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mencetak struk belanja.");
            e.printStackTrace();
        }
    }

    public void inputOptionKonfirmasiPembayaran() {
        orderView.displayOptionKonfirmasiPembayaran();
        int pilihanKonfirmasi = scanner.nextInt();
        konfirmasiPilihan(pilihanKonfirmasi);
    }

    public void mainMenuCustomer(UUID pilihanKedai) {
        //OPTIONAL CLASS
        selectedMerchantId = Optional.ofNullable(selectedMerchantId)
                .orElse(pilihanKedai);

        if (pilihanKedai != null) {
            System.out.println("Menu yang tersedia:");
            productController.displayMenusCustomer(pilihanKedai);
            displayOption();
            menuPilihan();
        } else {
            System.out.println("Merchant not found or has no products.");
        }
    }

    public void displayOption() {
        System.out.println("""
                99. Pesan dan Bayar
                00. Kembali
                0. Keluar Aplikasi
                """);
    }
}
