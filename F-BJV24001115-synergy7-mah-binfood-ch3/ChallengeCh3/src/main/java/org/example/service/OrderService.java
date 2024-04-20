package org.example.service;

import org.example.model.entity.Order;
import org.example.model.entity.OrderDetail;

import java.util.Map;

public interface OrderService {

    Map<Long, OrderDetail> getPesananList();
    Order createPesanan(Order pesanan);
    int totalHarga();
    int totalPembelian();
}
