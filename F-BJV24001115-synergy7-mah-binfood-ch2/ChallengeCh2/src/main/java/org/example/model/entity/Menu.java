package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.view.MenuView;

@Setter
@Getter
@NoArgsConstructor
public class Menu {
    private Long id;
    private String namaMenu;
    private String kode;
    private int hargaMenu;

public Menu(String namaMenu, int hargaMenu){

}
}
