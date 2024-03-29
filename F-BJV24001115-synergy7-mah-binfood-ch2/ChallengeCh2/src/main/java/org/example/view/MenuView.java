package org.example.view;

import org.example.Data;
import org.example.controller.MenuController;
import org.example.controller.PesananController;
import org.example.model.entity.Menu;

import java.util.Map;
import java.util.Set;

public class MenuView {
    MenuController menuController = new MenuController();
    PesananController pesananController = new PesananController();
    public void displatTitle(){
        System.out.print("""
                ==========================
                Selamat Datang di BinarFud
                ========================== 
                
                Silahkan pilih makanan: 
                """);
    }
    public void displayOption(){
        System.out.println("""
                99. Pesan dan Bayar
                0. Keluar Aplikasi
                """);
        int pilihanMenu = menuController.getInput();
        pesananController.menuPilihan(pilihanMenu);
    }


    private void displayMenu(Menu menu){
        System.out.println(menu.getId()+" | "+menu.getNamaMenu()+" | "+menu.getHargaMenu());
    }

    public void displayMenus(Map<Long, Menu> menuMap){
        Set<Long> setId =Data.menuMap.keySet();
        for (Long key : setId) {
            displayMenu(Data.menuMap.get(key));
        }
    }
}
