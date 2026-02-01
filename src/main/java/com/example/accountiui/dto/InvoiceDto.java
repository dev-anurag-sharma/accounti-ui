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
public class InvoiceDto {
    private Long id;
    private String invoiceNumber;
    private String type; // TAX_INVOICE, PROFORMA, CREDIT_NOTE, DEBIT_NOTE
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Long customerId;
    private String customerName;
    private String customerGstin;
    private BigDecimal subtotal;
    private BigDecimal cgst;
    private BigDecimal sgst;
    private BigDecimal igst;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private String status; // DRAFT, SENT, PAID, OVERDUE, CANCELLED
    private String notes;
    private List<InvoiceItemDto> items;
}
