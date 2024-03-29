package org.example.controller;

import org.example.model.entity.Menu;
import org.example.model.entity.Pesanan;
import org.example.service.MenuService;
import org.example.service.MenuServiceImpl;
import org.example.view.MenuView;
import org.example.view.PesananView;

import java.util.Map;
import java.util.Scanner;

public class MenuController {
    MenuService menuService = new MenuServiceImpl();
    Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        MenuView menuView = new MenuView();
        menuView.displatTitle();
        displayMenus();
        menuView.displayOption();
    }

    public void displayMenus() {
        Map<Long, Menu> menuMap = menuService.getMenuList();
        MenuView menuView = new MenuView();
        menuView.displayMenus(menuMap);
    }

    public void displayMenu(Long menuId) {
        Menu menu = menuService.read(menuId);
        if (menu != null) {
            System.out.println(menu.getNamaMenu() + " | " + menu.getHargaMenu());
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }

    public int getInput() {
        System.out.print("=> ");
        return scanner.nextInt();
    }
}
