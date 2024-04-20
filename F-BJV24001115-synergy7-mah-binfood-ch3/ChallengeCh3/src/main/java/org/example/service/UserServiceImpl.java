package org.example.service;

import org.example.Data;
import org.example.controller.MenuController;
import org.example.controller.MerchantController;
import org.example.model.entity.Role;
import org.example.model.entity.Users;

public class UserServiceImpl implements UserService {
    MenuController menuController = new MenuController();
    MerchantController merchantController = new MerchantController();

    @Override
    public String register(String username, String email_address, String password, Role role) {
        for (Users user : Data.userMap.values()) {
            if (user.getEmail_address().equals(email_address)) {
                return "Email already registered";
            }
        }

        long newId = generateNewId();
        Users newUser = new Users();
        newUser.setId(newId);
        newUser.setUsername(username);
        newUser.setEmail_address(email_address);
        newUser.setPassword(password);
        newUser.setRole(role);

        Data.userMap.put(newId, newUser);

        if (role == Role.MERCHANT) {
            menuController.mainMenuMerchant();
        } else if (role == Role.CUSTOMER) {
            merchantController.menuKedaiCustomer();
        }

        return "Registration successful";
    }

    @Override
    public String login(String email_address, String password) {
        for (Users user : Data.userMap.values()) {
            if (user.getEmail_address().equals(email_address) && user.getPassword().equals(password)) {
                Role role = user.getRole();
                if (role == Role.MERCHANT) {
                    menuController.mainMenuMerchant();
                } else if (role == Role.CUSTOMER) {
                    merchantController.menuKedaiCustomer();
                } else {
                    return "Invalid role";
                }
                return "Login successful";
            }
        }

        return "Email or password is incorrect. Please try again.";
    }

    private long generateNewId() {
        long maxId = 0;
        for (long id : Data.userMap.keySet()) {
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
}
