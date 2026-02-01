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
public class InventoryDto {
    private Long id;
    private String productCode;
    private String productName;
    private String category;
    private String description;
    private String unit;
    private BigDecimal purchaseRate;
    private BigDecimal sellingRate;
    private BigDecimal openingStock;
    private BigDecimal currentStock;
    private BigDecimal reorderLevel;
    private BigDecimal stockValue;
    private String hsnCode;
    private BigDecimal gstRate;
    private boolean active;
}
