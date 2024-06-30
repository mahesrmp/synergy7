package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.model.dto.MerchantCreateDto;
import com.example.xx2.model.dto.MerchantDto;
import com.example.xx2.model.dto.ReportDto;
import com.example.xx2.repository.MerchantRepository;
import com.example.xx2.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public List<Merchant> getAllMerchantOpen() {
        return merchantRepository.findByOpen(true);
    }

    @Override
    public MerchantDto create(MerchantCreateDto merchantCreateDto) {
        Merchant merchant = new Merchant();
        merchant.setMerchant_name(merchantCreateDto.getMerchant_name());
        merchant.setMerchant_location(merchantCreateDto.getMerchant_location());
        merchant.setOpen((merchantCreateDto.isOpen()));
        merchantRepository.save(merchant);

        return modelMapper.map(merchant, MerchantDto.class);
    }

//    @Override
//    public Merchant updateOpen(UUID idMerchant) {
//        Merchant merchant = merchantRepository.findById(idMerchant).orElseThrow();
//        merchant.setOpen(false);
//        return merchantRepository.save(merchant);
//    }

    @Override
    public MerchantDto updateOpen(UUID idMerchant) {
        Merchant merchant = merchantRepository.findById(idMerchant).orElseThrow();
        merchant.setOpen(true);
        Merchant updatedMerchant = merchantRepository.save(merchant);
        return convertToDto(updatedMerchant);
    }

    @Override
    public MerchantDto updateClose(UUID idMerchant) {
        Merchant merchant = merchantRepository.findById(idMerchant).orElseThrow();
        merchant.setOpen(false);
        Merchant updatedMerchant = merchantRepository.save(merchant);
        return convertToDto(updatedMerchant);
    }

    private MerchantDto convertToDto(Merchant merchant) {
        MerchantDto dto = new MerchantDto();
        dto.setMerchant_name(merchant.getMerchant_name());
        dto.setMerchant_location(merchant.getMerchant_location());
        dto.setOpen(merchant.isOpen());
        return dto;
    }

    @Override
    public void deleteMerchant(UUID idMerchant) {
        merchantRepository.deleteById(idMerchant);
    }

    @Override
    public List<ReportDto> generateMerchantReport(LocalDate startDate, LocalDate endDate, String period) {
        List<Orders> orders = orderRepository.findByOrderTimeBetween(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());

        // Group and calculate revenue based on the period (weekly or monthly)
        Map<String, Double> revenueData = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if ("weekly".equalsIgnoreCase(period)) {
            orders.stream().collect(Collectors.groupingBy(order -> getWeekStartDate(order.getOrder_time().toLocalDate())))
                    .forEach((weekStartDate, weeklyOrders) -> {
                        double totalRevenue = weeklyOrders.stream()
                                .flatMap(order -> order.getOrderDetails().stream())
                                .mapToDouble(OrderDetail::getTotal_price)
                                .sum();
                        revenueData.put(weekStartDate.format(formatter), totalRevenue);
                    });
        } else if ("monthly".equalsIgnoreCase(period)) {
            orders.stream().collect(Collectors.groupingBy(order -> order.getOrder_time().toLocalDate().withDayOfMonth(1)))
                    .forEach((monthStartDate, monthlyOrders) -> {
                        double totalRevenue = monthlyOrders.stream()
                                .flatMap(order -> order.getOrderDetails().stream())
                                .mapToDouble(OrderDetail::getTotal_price)
                                .sum();
                        revenueData.put(monthStartDate.format(formatter), totalRevenue);
                    });
        } else {
            throw new IllegalArgumentException("Invalid period specified: " + period);
        }

        // Prepare data for response
        List<ReportDto> reportData = new ArrayList<>();
        for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
            reportData.add(new ReportDto(entry.getKey(), entry.getValue()));
        }

        return reportData;
    }

    private LocalDate getWeekStartDate(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }
}
