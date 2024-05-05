package org.example;

import org.example.controller.MenuController;

public class Main {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        Data.init();
        menuController.mainMenu();
    }
}