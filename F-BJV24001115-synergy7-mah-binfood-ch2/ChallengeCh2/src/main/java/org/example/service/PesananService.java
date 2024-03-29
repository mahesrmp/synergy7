package org.example.service;

import org.example.model.entity.Pesanan;

import java.util.Map;

public interface PesananService {

    Map<Long, Pesanan> getPesananList();
    Pesanan createPesanan(Pesanan pesanan);
    int totalHarga();
    int totalPembelian();
}
