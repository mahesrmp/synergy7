package com.example.xx2.service;

import com.example.xx2.model.Users;
import com.example.xx2.model.dto.OrderDetailResponseDto;
import com.example.xx2.model.dto.OrderResponseDto;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    public byte[] generateInvoice(UUID userId) throws JRException {
        Users user = userService.getUserById(userId);
        List<OrderResponseDto> orders = orderService.getOrdersByUserId(userId);

        List<Map<String, Object>> orderDetailsData = new ArrayList<>();
        double totalAmount = 0.0;
        for (OrderResponseDto order : orders) {
            for (OrderDetailResponseDto orderDetail : order.getOrderDetails()) {
                Map<String, Object> detailData = new HashMap<>();
                detailData.put("product_name", orderDetail.getProduct().getProduct_name());
                detailData.put("quantity", orderDetail.getQuantity());
                detailData.put("total_price", orderDetail.getTotal_price());
                orderDetailsData.add(detailData);

                totalAmount += orderDetail.getTotal_price();
            }
        }
        log.info("Order details size: {}", orderDetailsData.size());
        log.info("Order details: {}", orderDetailsData);

        InputStream invoiceTemplate = getClass().getResourceAsStream("/report/invoice_template.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(invoiceTemplate);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDetailsData);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", user.getUsername());
        parameters.put("orderDetailsDataSource", dataSource);
        parameters.put("totalAmount", totalAmount);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
