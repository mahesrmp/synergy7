package org.example.view;

import org.example.controller.UserController;
import org.example.model.entity.Role;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.util.Scanner;

public class UserView {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

    public void displayAuth(){
        UserController userController = new UserController();
        System.out.print("""
                ==========================
                Selamat Datang di BinarFud
                ========================== 
                
                Sudah memiliki akun?
                1 untuk Ya / 0 untuk Belum: 
                """);
        int pilihanAuth = scanner.nextInt();
        userController.authPilihan(pilihanAuth);
    }

    public void formLogin() {
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
//                System.out.println("Silakan coba lagi.");
            }
        }while(!loginSuccess);

    }

    public void formRegister() {
        System.out.println("=== Register ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email address: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println("""
                Pilih role mu:
                1. MERCHANT
                2. CUSTOMER
                """);
        String role = scanner.nextLine();
        userService.register(username, email, password, Role.valueOf(role));
    }

}
