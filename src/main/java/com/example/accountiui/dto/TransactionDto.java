package com.example.accountiui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private LocalDate date;
    private String voucherNumber;
    private String voucherType; // PAYMENT, RECEIPT, JOURNAL, CONTRA
    private Long accountId;
    private String accountName;
    private String particulars;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal runningBalance;
    private String referenceNumber;
    private String description;
}
