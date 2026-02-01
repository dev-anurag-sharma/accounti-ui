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
public class PartyDto {
    private Long id;
    private String name;
    private String type; // CUSTOMER, SUPPLIER, BOTH
    private String contactPerson;
    private String phone;
    private String email;
    private String gstin;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private BigDecimal openingBalance;
    private BigDecimal currentBalance;
    private BigDecimal creditLimit;
    private String paymentTerms;
    private boolean active;
}
