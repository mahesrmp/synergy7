package com.example.xx2.service;

import com.example.xx2.controller.MerchantController;
import com.example.xx2.controller.ProductController;
import com.example.xx2.model.Merchant;
import com.example.xx2.model.Role;
import com.example.xx2.model.Users;
import com.example.xx2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    MerchantController merchantController;
    @Autowired
    ProductController productController;
    public static Users currentUserId;



    @Override
    public String register(String username, String email, String password, Role role) {
        if (userRepository.existsByEmail(email)) {
            return "Email already registered";
        }

        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);

        userRepository.save(newUser);
        if (role == Role.MERCHANT) {
            merchantController.tambahMerchant(newUser);
            currentUserId = newUser;
            productController.mainMenuMerchant();
        } else if (role == Role.CUSTOMER) {
            currentUserId = newUser;
            merchantController.menuKedaiCustomer();
        }
        return "Registration successful";
    }

    @Override
    public String login(String email, String password) {
        Optional<Users> optionalPengguna = userRepository.findByEmail(email);

        if (optionalPengguna.isPresent()) {
            Users pengguna = optionalPengguna.get();

            if (pengguna.getPassword().equals(password)) {
                Role role = pengguna.getRole();
                if (role == Role.MERCHANT) {
                    currentUserId = pengguna;
                    productController.mainMenuMerchant();
                } else if (role == Role.CUSTOMER) {
                    currentUserId = pengguna;
                    merchantController.menuKedaiCustomer();
                } else {
                    return "Invalid role";
                }
                return "Login successful";
            }else {
                return "Password is incorrect. Please try again.";
            }
        }else {
            return "Email is incorrect. Please try again.";
        }
    }

    public static Merchant getMerchantId(){
        if (currentUserId != null){
            return currentUserId.getMerchant();
        };
        return null;
    }

}
