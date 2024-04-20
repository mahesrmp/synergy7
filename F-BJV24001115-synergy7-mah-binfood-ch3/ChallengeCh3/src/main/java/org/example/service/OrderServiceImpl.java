package org.example.service;

import org.example.Data;
import org.example.model.entity.Order;
import org.example.model.entity.OrderDetail;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public Map<Long, OrderDetail> getPesananList() {
        return Data.pesananDetailMap;
    }
    private static long lastId = 0;
    @Override
    public Order createPesanan(Order pesanan) {
        Long pesananId = ++lastId;
        pesanan.setId(pesananId);
        pesanan.setOrder_time(LocalDateTime.now());
        Data.pesananMap.put(pesananId, pesanan);
        for (OrderDetail orderDetail : pesanan.getOrderDetails()) {
            orderDetail.setOrder(pesanan);
            Data.pesananDetailMap.put(pesananId, orderDetail);
        }
        return pesanan;
    }

    @Override
    public int totalHarga() {
        int totalHarga = 0;
        for (Order pesanan : Data.pesananMap.values()) {
            for (OrderDetail orderDetail : pesanan.getOrderDetails()) {
                totalHarga += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
            }
        }
        return totalHarga;
    }

    @Override
    public int totalPembelian() {
        int totalPembelian = 0;
        for (Order pesanan : Data.pesananMap.values()) {
            for (OrderDetail orderDetail : pesanan.getOrderDetails()) {
                totalPembelian += orderDetail.getQuantity();
            }
        }
        return totalPembelian;
    }
}
