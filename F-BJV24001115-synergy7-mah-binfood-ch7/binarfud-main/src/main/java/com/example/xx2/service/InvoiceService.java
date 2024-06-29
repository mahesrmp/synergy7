package com.example.xx2.service;

import net.sf.jasperreports.engine.JRException;

import java.time.LocalDateTime;
import java.util.UUID;

public interface InvoiceService {
    byte[] generateInvoice(UUID userId) throws JRException;
//    byte[] generateReportingMerchant(UUID merchantId, LocalDateTime startDate, LocalDateTime endDate) throws JRException;
}
