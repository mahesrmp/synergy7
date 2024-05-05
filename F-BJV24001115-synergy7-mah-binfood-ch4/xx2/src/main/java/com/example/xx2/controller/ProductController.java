package com.example.xx2.controller;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Product;
import com.example.xx2.service.MerchantService;
import com.example.xx2.service.ProductService;
import com.example.xx2.view.ProductView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.example.xx2.service.UserServiceImpl.currentUserId;

@Component
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final Scanner scanner;
    private final ProductView productView;
    private final MerchantService merchantService;

    @Autowired
    public ProductController(ProductService productService, Scanner scanner, ProductView productView, MerchantService merchantService) {
        this.productService = productService;
        this.scanner = scanner;
        this.productView = productView;
        this.merchantService = merchantService;
    }

    void displayMenusCustomer(UUID selectedMerchantId) {
        List<Product> menuList = productService.getMenuList(selectedMerchantId);
        if (menuList != null) {
            displayMenus(menuList);
        } else {
            System.out.println("Menu not available.");
        }
    }

    public void displayMenus() {
        UUID merchantId = merchantService.getIdMerchantByUserId(currentUserId.getId());
        List<Product> menuList = productService.getMenuList(merchantId);
        if (menuList != null) {
            displayMenus(menuList);
        } else {
            System.out.println("Menu not available.");
        }
    }

    public void displayMenus(List<Product> productList) {
        int[] counter = {1};
        productList.stream()
                .forEach(product -> System.out.println((counter[0]++) + " | " + product.getProduct_name() + " | " + product.getPrice()));
    }

    public void mainMenuMerchant() {
        System.out.println("Menu yang tersedia:");
        displayMenus();
        productView.displayOptionMerchant();
        int pilihanMenu = scanner.nextInt();
        menuPilihan(pilihanMenu);
    }

    public void menuPilihan(int pilihanMenu) {
        try {
            if (isValidMenuOption(pilihanMenu)) {
                if (pilihanMenu == 1) {
                    tambahProduct();
                } else if (pilihanMenu == 2) {
                    updateProduct();
                } else if (pilihanMenu == 3) {
                    deleteProduct();
                } else if (pilihanMenu == 4) {
                    kelolaToko();
                }
            } else {
                throw new InputMismatchException("Inputan tidak valid. Harap masukkan pilihan yang sesuai.");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isValidMenuOption(int option) {
        return option >= 1 && option <= 4 || option == 00;
    }


    public void tambahProduct() {
        try {
            System.out.print("Nama Menu: ");
            scanner.nextLine();
            String nama_menu = scanner.nextLine();
            System.out.print("Price: ");
            Integer price = scanner.nextInt();
            scanner.nextLine();
            productService.createProduct(price, nama_menu, merchantService.getIdMerchantByUserId(currentUserId.getId()));
            mainMenuMerchant();
        } catch (Exception e) {
            System.out.println("Gagal menambahkan produk. Silakan coba lagi.");
            tambahProduct();
        }
    }

    public void updateProduct() {
        System.out.println("Pilih Menu yang ingin di edit");
        displayMenus();
        System.out.print("=> ");
        int pilihanMenu = scanner.nextInt();
        scanner.nextLine();
        List<Product> productList = productService.getMenuList(merchantService.getIdMerchantByUserId(currentUserId.getId()));
        if (pilihanMenu >= 1 && pilihanMenu <= productList.size()) {
            UUID selectedProductId = productList.get(pilihanMenu - 1).getId();

            Product selectedProduct = productService.getProductById(selectedProductId);
            if (selectedProduct != null) {
                prosesUpdateProduct(selectedProduct);
            } else {
                System.out.println("Produk tidak ditemukan.");
            }
        } else {
            System.out.println("Indeks produk tidak valid.");
        }
    }

    public void prosesUpdateProduct(Product selectedProduct) {
        try {
            System.out.println("Update Produk: " + selectedProduct.getProduct_name() + "     " + selectedProduct.getPrice());

            System.out.print("Masukkan nama baru: ");
            String newName = scanner.nextLine();

            System.out.print("Masukkan harga baru: ");
            int newPrice = scanner.nextInt();

            productService.updateProduct(newPrice, newName, selectedProduct.getId());
            log.info("Produk berhasil diperbarui.");

            mainMenuMerchant();
        } catch (Exception e) {
            log.info("Gagal memperbarui produk.");
            updateProduct();
        }
    }

    public void deleteProduct() {
        System.out.println("Pilih Menu yang ingin dihapus");
        displayMenus();
        System.out.print("=> ");
        int pilihanMenu = scanner.nextInt();

        List<Product> productList = productService.getMenuList(merchantService.getIdMerchantByUserId(currentUserId.getId()));
        if (pilihanMenu >= 0 && pilihanMenu <= productList.size()) {
            UUID selectedProductId = productList.get(pilihanMenu - 1).getId();
            boolean successDeleteProduct = productService.deleteProduct(selectedProductId);
            if (successDeleteProduct) {
                log.info("Produk berhasil dihapus.");
            } else {
                log.info("Gagal menghapus produk.");
            }
            mainMenuMerchant();
        } else {
            System.out.println("Indeks produk tidak valid.");
        }
    }

    public void kelolaToko() {
        boolean statusToko = merchantService.getStatusToko(merchantService.getIdMerchantByUserId(currentUserId.getId()));

        if (statusToko) {
            tutupToko();
        } else {
            bukaToko();
        }
    }

    private void tutupToko() {
        boolean success = merchantService.tutupToko(merchantService.getIdMerchantByUserId(currentUserId.getId()));
        if (success) {
            log.info("Toko berhasil ditutup.");
        } else {
            log.info("Gagal menutup toko.");
        }
        mainMenuMerchant();
    }

    private void bukaToko() {
        boolean success = merchantService.bukaToko(merchantService.getIdMerchantByUserId(currentUserId.getId()));
        if (success) {
            log.info("Toko berhasil dibuka.");
        } else {
            log.info("Gagal membuka toko.");
        }
        mainMenuMerchant();
    }
}
