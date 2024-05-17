package com.example.xx2.controller;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.dto.MerchantCreateDto;
import com.example.xx2.model.dto.MerchantDto;
import com.example.xx2.model.dto.MerchantRequestOpenDto;
import com.example.xx2.model.dto.ReportDto;
import com.example.xx2.service.MerchantService;
import jakarta.websocket.OnClose;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("merchant")
@Slf4j
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("dto")
    public List<MerchantDto> getAllMerchantDto(){
        return merchantService.getAll()
                .stream()
                .map(merchant -> modelMapper.map(merchant, MerchantDto.class))
                .toList();
    }

    @GetMapping("open")
    public List<MerchantDto> getAllMerchantOpen(){
        return merchantService.getAllMerchantOpen()
                .stream()
                .map(merchant -> modelMapper.map(merchant, MerchantDto.class))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> add(@RequestBody MerchantCreateDto merchantCreateDto){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("merchant", merchantService.create(merchantCreateDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PatchMapping("/open/{id}")
//    public Merchant update(@PathVariable("id") UUID idMerchant){
//        return merchantService.updateOpen(idMerchant);
//    }

    @PatchMapping("/open/{id}")
    public ResponseEntity<Map<String, Object>> updateOpenMerchant(@PathVariable("id") UUID idMerchant){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("merchant", merchantService.updateOpen(idMerchant));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/close/{id}")
    public ResponseEntity<Map<String, Object>> updateCloseMerchant(@PathVariable("id") UUID idMerchant){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("merchant", merchantService.updateClose(idMerchant));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMerchant(@PathVariable("id") UUID idMerchant) {
        try {
            merchantService.deleteMerchant(idMerchant);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report")
    public List<ReportDto> getMerchantReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam String period) {
        return merchantService.generateMerchantReport(startDate, endDate, period);
    }
}
