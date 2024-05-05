package org.example;

import org.example.model.entity.Menu;
import org.example.model.entity.Pesanan;

import java.util.HashMap;
import java.util.Map;

public class Data {
    public static Map<Long, Menu> menuMap = new HashMap<>();

    public static Map<Long, Pesanan> pesananMap = new HashMap<>();

    public static void init(){
        Menu menu1 = new Menu();
        menu1.setId(1L);
        menu1.setNamaMenu("Nasi Goreng");
        menu1.setHargaMenu(15000);

        Menu menu2 = new Menu();
        menu2.setId(2L);
        menu2.setNamaMenu("Mie Goreng");
        menu2.setHargaMenu(13000);

        Menu menu3 = new Menu();
        menu3.setId(3L);
        menu3.setNamaMenu("Nasi + Ayam");
        menu3.setHargaMenu(18000);

        Menu menu4 = new Menu();
        menu4.setId(4L);
        menu4.setNamaMenu("Es Teh Manis");
        menu4.setHargaMenu(3000);

        Menu menu5 = new Menu();
        menu5.setId(5L);
        menu5.setNamaMenu("Es Jeruk");
        menu5.setHargaMenu(5000);

        menuMap.put(menu1.getId(), menu1);
        menuMap.put(menu2.getId(), menu2);
        menuMap.put(menu3.getId(), menu3);
        menuMap.put(menu4.getId(), menu4);
        menuMap.put(menu5.getId(), menu5);
    }
}
