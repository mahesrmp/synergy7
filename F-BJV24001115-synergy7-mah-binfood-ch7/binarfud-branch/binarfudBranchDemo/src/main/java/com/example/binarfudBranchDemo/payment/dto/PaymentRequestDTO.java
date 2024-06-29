package com.example.binarfudBranchDemo.payment.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private String name;
    private String number;
    private String email;
    private String address;
    private int billValue;
    private String cardNumber;
    private String cardHolder;
    private String dateValue;
    private String cvc;
}
