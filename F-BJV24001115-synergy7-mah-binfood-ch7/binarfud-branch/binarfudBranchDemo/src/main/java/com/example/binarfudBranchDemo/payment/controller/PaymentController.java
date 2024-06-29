package com.example.binarfudBranchDemo.payment.controller;

import com.example.binarfudBranchDemo.payment.dto.PaymentDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentRequestDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentResponseDTO;
import com.example.binarfudBranchDemo.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO createdPayment = paymentService.createPayment(paymentRequestDTO);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<PaymentDTO> getAllPayment(){
        return paymentService.getAllPayments();
    }
}
