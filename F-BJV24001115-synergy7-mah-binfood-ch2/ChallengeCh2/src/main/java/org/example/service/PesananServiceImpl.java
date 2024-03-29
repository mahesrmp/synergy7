package org.example.service;

import org.example.Data;
import org.example.model.entity.Pesanan;

import java.time.LocalDateTime;
import java.util.Map;

public class PesananServiceImpl implements PesananService{
    @Override
    public Map<Long, Pesanan> getPesananList() {
        return Data.pesananMap;
    }
    private static long lastId = 0;
    @Override
    public Pesanan createPesanan(Pesanan pesanan) {
        Long pesananId = ++lastId;
        pesanan.setId(pesananId);
        pesanan.setCreatedTime(LocalDateTime.now());
        Data.pesananMap.put(pesanan.getId(), pesanan);
        return pesanan;
    }

    @Override
    public int totalHarga() {
        int totalHarga = 0;
        for (Pesanan pesanan : Data.pesananMap.values()) {
            totalHarga += pesanan.getMenu().getHargaMenu() * pesanan.getJumlahPesanan();
        }
        return totalHarga;
    }

    @Override
    public int totalPembelian() {
        int totalPembelian = 0;
        for (Pesanan pesanan : Data.pesananMap.values()){
            totalPembelian += pesanan.getJumlahPesanan();
        }
        return totalPembelian;
    }
}
