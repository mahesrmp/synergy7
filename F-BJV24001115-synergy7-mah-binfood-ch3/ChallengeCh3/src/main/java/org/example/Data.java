package org.example;

import org.example.model.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.model.entity.Role.CUSTOMER;
import static org.example.model.entity.Role.MERCHANT;

public class Data {
    public static Map<Long, Product> menuMap = new HashMap<>();
    public static Map<Long, OrderDetail> pesananDetailMap = new HashMap<>();
    public static Map<Long, Order> pesananMap = new HashMap<>();

    public static Map<Long, Merchant> merchantMap = new HashMap<>();

    public static Map<Long, Users> userMap = new HashMap<>();

    public static void init(){
        Product menu1 = new Product();
        menu1.setId(1L);
        menu1.setProduct_name("Nasi Goreng");
        menu1.setPrice(15000);
        menu1.setMerchant_id(1L);

        Product menu2 = new Product();
        menu2.setId(2L);
        menu2.setProduct_name("Mie Goreng");
        menu2.setPrice(13000);
        menu2.setMerchant_id(1L);

        Product menu3 = new Product();
        menu3.setId(3L);
        menu3.setProduct_name("Nasi + Ayam");
        menu3.setPrice(18000);
        menu3.setMerchant_id(1L);

        Product menu4 = new Product();
        menu4.setId(4L);
        menu4.setProduct_name("Es Teh Manis");
        menu4.setPrice(3000);
        menu4.setMerchant_id(1L);

        Product menu5 = new Product();
        menu5.setId(5L);
        menu5.setProduct_name("Es Jeruk");
        menu5.setPrice(5000);
        menu5.setMerchant_id(2L);

        Users user1 = new Users();
        user1.setId(1L);
        user1.setUsername("Mahes");
        user1.setEmail_address("mahes@gmail.com");
        user1.setPassword("mahes");
        user1.setRole(MERCHANT);

        Users user2 = new Users();
        user2.setId(2L);
        user2.setUsername("Ahmad");
        user2.setEmail_address("ahmad@gmail.com");
        user2.setPassword("ahmad");
        user2.setRole(CUSTOMER);

        Users user3 = new Users();
        user3.setId(3L);
        user3.setUsername("Bambang");
        user3.setEmail_address("bambang@gmail.com");
        user3.setPassword("bambang");
        user3.setRole(MERCHANT);

        Merchant merchant1 = new Merchant();
        merchant1.setId(1L);
        merchant1.setMerchant_name("Kedai Mahes");
        merchant1.setMerchant_location("Jl Kemenangan");
        merchant1.setOpen(true);

        Merchant merchant2 = new Merchant();
        merchant2.setId(2L);
        merchant2.setMerchant_name("Kedai Bambang");
        merchant2.setMerchant_location("Jl Kemenangan");
        merchant2.setOpen(true);

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(user3.getId(), user3);

        merchantMap.put(merchant1.getId(), merchant1);
        merchantMap.put(merchant2.getId(), merchant2);

        menuMap.put(menu1.getId(), menu1);
        menuMap.put(menu2.getId(), menu2);
        menuMap.put(menu3.getId(), menu3);
        menuMap.put(menu4.getId(), menu4);
        menuMap.put(menu5.getId(), menu5);
    }
}
