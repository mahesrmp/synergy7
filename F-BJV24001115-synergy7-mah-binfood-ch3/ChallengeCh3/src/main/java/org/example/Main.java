package org.example;

import org.example.controller.UserController;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Data.init();
        userController.authMenu();
    }
}