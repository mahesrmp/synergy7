package com.example.xx2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportDto {
    private String period;
    private Double totalRevenue;
}
