package com.example.xx2.model.dto;

import lombok.Data;

@Data
public class MerchantDto {
    private String merchant_name, merchant_location;
    private boolean open;
}
