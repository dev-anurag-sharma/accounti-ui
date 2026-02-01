package com.example.accountiui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal rate;
    private BigDecimal discount;
    private BigDecimal taxRate;
    private BigDecimal amount;
}
