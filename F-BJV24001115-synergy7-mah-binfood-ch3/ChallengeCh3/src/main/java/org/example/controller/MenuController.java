package org.example.controller;

import org.example.Data;
import org.example.model.entity.Merchant;
import org.example.model.entity.Product;
import org.example.service.ProductService;
import org.example.service.ProductServiceImpl;
import org.example.view.MenuView;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class MenuController {
    ProductService menuService = new ProductServiceImpl();
    Scanner scanner = new Scanner(System.in);

    public static Long selectedMerchantId = null;
    public void mainMenuCustomer(Long pilihanKedai) {
        //OPTIONAL CLASS
        selectedMerchantId = Optional.ofNullable(selectedMerchantId)
                .orElse(pilihanKedai);

        MenuView menuView = new MenuView();

        Optional<Merchant> selectedMerchant = Optional.ofNullable(Data.merchantMap.get(pilihanKedai));

        selectedMerchant.ifPresent(merchant -> {
            System.out.println("Menu " + merchant.getMerchant_name() + ":");
            menuView.displayTitle();
            //STREAM
            //LAMBDA
            Data.menuMap.values().stream()
                    .filter(product -> product.getMerchant_id().equals(pilihanKedai))
                    .forEach(product -> System.out.println(product.getId() + ": " + product.getProduct_name() + "   |   " + product.getPrice()));
            menuView.displayOption();
        });

        if (selectedMerchant.isEmpty()) {
            System.out.println("Merchant not found.");
        }
    }

    public void mainMenuMerchant() {
        MenuView menuView = new MenuView();
        menuView.displayTitle();
        displayMenus();
        menuView.displayOption();
    }

    public void displayMenus() {
        Map<Long, Product> menuMap = menuService.getMenuList();
        MenuView menuView = new MenuView();
        menuView.displayMenus(menuMap);
    }

    public int getInput() {
        System.out.print("=> ");
        return scanner.nextInt();
    }
}
