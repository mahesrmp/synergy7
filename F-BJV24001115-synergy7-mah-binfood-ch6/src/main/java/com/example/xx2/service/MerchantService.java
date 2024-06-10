package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.dto.MerchantCreateDto;
import com.example.xx2.model.dto.MerchantDto;
import com.example.xx2.model.dto.ReportDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> getAll();

    MerchantDto create(MerchantCreateDto merchantCreateDto);

    MerchantDto updateOpen(UUID idMerchant);

    MerchantDto updateClose(UUID idMerchant);

    List<Merchant> getAllMerchantOpen();

    void deleteMerchant(UUID idMerchant);

    List<ReportDto> generateMerchantReport(LocalDate startDate, LocalDate endDate, String period);

//    MerchantRequestOpenDto updateOpen(UUID idMerchant, MerchantRequestOpenDto merchantRequestOpenDto);
}
