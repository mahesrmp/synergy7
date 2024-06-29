package com.example.binarfudBranchDemo.payment.service;

import com.example.binarfudBranchDemo.payment.dto.PaymentDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentRequestDTO;
import com.example.binarfudBranchDemo.payment.dto.PaymentResponseDTO;
import com.example.binarfudBranchDemo.payment.model.Payment;
import com.example.binarfudBranchDemo.payment.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = convertToEntity(paymentRequestDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToResponseDTO(savedPayment);
    }

    private Payment convertToEntity(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRequestDTO, payment);
        return payment;
    }

    private PaymentResponseDTO convertToResponseDTO(Payment payment) {
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        BeanUtils.copyProperties(payment, responseDTO);
        return responseDTO;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }
}
