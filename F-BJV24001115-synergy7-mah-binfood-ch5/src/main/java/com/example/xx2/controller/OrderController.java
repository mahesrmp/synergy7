package com.example.xx2.controller;

import com.example.xx2.model.OrderDetail;
import com.example.xx2.model.Orders;
import com.example.xx2.model.dto.OrderRequestDto;
import com.example.xx2.model.dto.OrderResponseDto;
import com.example.xx2.service.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDTO) {
        return orderService.createOrder(orderRequestDTO);
    }

    @GetMapping("/all")
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersByUserId(@PathVariable UUID userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/completed")
    public List<OrderResponseDto> getCompletedOrders() {
        return orderService.getCompletedOrders();
    }

    @GetMapping("/report/{userId}")
    public ResponseEntity<Resource> generateInvoice(@PathVariable UUID userId) {
        try {
            byte[] pdfBytes = invoiceService.generateInvoice(userId);
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (JRException e) {
            log.error("Error generating invoice: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/merchant")
//    public ResponseEntity<byte[]> generateReportingMerchant(
//            @RequestParam UUID merchantId,
//            @RequestParam LocalDateTime startDate,
//            @RequestParam LocalDateTime endDate) {
//        try {
//            byte[] pdfBytes = invoiceService.generateReportingMerchant(merchantId, startDate, endDate);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merchant_report.pdf")
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .body(pdfBytes);
//        } catch (JRException e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }
}
