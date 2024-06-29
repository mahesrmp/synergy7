package com.example.binarfudBranchDemo.payment.service;

import com.example.binarfudBranchDemo.payment.dto.PaymentDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentRequestDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO);

    List<PaymentDTO> getAllPayments();
}
