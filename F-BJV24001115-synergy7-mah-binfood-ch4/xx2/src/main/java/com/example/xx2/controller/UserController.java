package com.example.xx2.controller;

import com.example.xx2.model.Role;
import com.example.xx2.service.UserService;
import com.example.xx2.view.UserView;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class UserController {
    private final UserView userView;
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserView userView, UserService userService, Scanner scanner) {
        this.userView = userView;
        this.userService = userService;
        this.scanner = scanner;
    }


    public void authMenu() {
        userView.displayAuthMenu();
        int pilihanAuth = scanner.nextInt();
        authPilihan(pilihanAuth);
    }

    public void authPilihan(int pilihanAuth) {
        try {
            if (pilihanAuth == 1) {
                boolean loginSuccess = false;
                do {
                    System.out.println("=== Login ===");
                    System.out.print("Email address: ");
                    String email = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    String loginMessage = userService.login(email, password);
                    if (loginMessage.equals("Login successful")) {
                        loginSuccess = true;
                    } else {
                        System.out.println(loginMessage);
                    }
                } while (!loginSuccess);

            } else if (pilihanAuth == 0) {
                System.out.println("=== Register ===");
                System.out.print("Username: ");
                String username = scanner.nextLine();
                scanner.nextLine();

                System.out.print("Email address: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                System.out.println("""
                Pilih role mu:
                1. MERCHANT
                2. CUSTOMER
                """);
                String roleInput = scanner.nextLine();
                Role role = null;
                if (roleInput.equals("1")) {
                    role = Role.MERCHANT;
                } else if (roleInput.equals("2")) {
                    role = Role.CUSTOMER;
                } else {
                    System.out.println("Input role tidak valid");
                    return;
                }
                userService.register(username, email, password, role);
            } else {
                System.out.println("Inputan tidak valid. Harap Masukkan 1 untuk Ya sudah punya akun atau 0 untuk Belum memiliki akun");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            authMenu();
        }
    }
}
