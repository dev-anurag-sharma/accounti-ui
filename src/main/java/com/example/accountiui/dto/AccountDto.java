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
public class AccountDto {
    private Long id;
    private String code;
    private String name;
    private String type; // ASSET, LIABILITY, INCOME, EXPENSE, EQUITY
    private String category; // Current Asset, Fixed Asset, etc.
    private BigDecimal openingBalance;
    private BigDecimal currentBalance;
    private String description;
    private boolean active;
}
