package com.example.accountiui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private String purchaseNumber;
    private LocalDate purchaseDate;
    private Long supplierId;
    private String supplierName;
    private String supplierGstin;
    private BigDecimal subtotal;
    private BigDecimal cgst;
    private BigDecimal sgst;
    private BigDecimal igst;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String status; // DRAFT, RECEIVED, PAID, CANCELLED
    private String notes;
    private List<PurchaseItemDto> items;
}
