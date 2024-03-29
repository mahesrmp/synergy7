package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Pesanan {
    private Long id;
    private String kode;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Menu menu;
    private int jumlahPesanan;
}
