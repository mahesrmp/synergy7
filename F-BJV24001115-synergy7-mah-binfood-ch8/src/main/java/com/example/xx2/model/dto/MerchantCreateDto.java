package com.example.xx2.model.dto;

import lombok.Data;

@Data
public class MerchantCreateDto {
    private String merchant_name, merchant_location;
    private boolean open;
}
