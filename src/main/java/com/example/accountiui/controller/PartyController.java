package com.example.accountiui.controller;

import com.example.accountiui.dto.PartyDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/parties")
public class PartyController {

    @GetMapping("/customers")
    public String customers(Model model) {
        List<PartyDto> allParties = createDummyParties();
        List<PartyDto> customers = allParties.stream()
            .filter(p -> "CUSTOMER".equals(p.getType()) || "BOTH".equals(p.getType()))
            .collect(Collectors.toList());
        
        model.addAttribute("customers", customers);
        return "parties/customers";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        List<PartyDto> allParties = createDummyParties();
        List<PartyDto> suppliers = allParties.stream()
            .filter(p -> "SUPPLIER".equals(p.getType()) || "BOTH".equals(p.getType()))
            .collect(Collectors.toList());
        
        model.addAttribute("suppliers", suppliers);
        return "parties/suppliers";
    }

    private List<PartyDto> createDummyParties() {
        List<PartyDto> parties = new ArrayList<>();
        
        parties.add(PartyDto.builder()
            .id(1L).name("Sharma Traders").type("CUSTOMER")
            .contactPerson("Rajesh Sharma").phone("9876543210")
            .email("rajesh@sharmatraders.com").gstin("27AABCS1234F1Z5")
            .address("123, Main Road, Nehru Nagar")
            .city("Mumbai").state("Maharashtra").pincode("400001")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(BigDecimal.ZERO)
            .creditLimit(new BigDecimal("500000.00"))
            .paymentTerms("Net 30 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(2L).name("Kumar & Sons").type("CUSTOMER")
            .contactPerson("Vijay Kumar").phone("9876543211")
            .email("vijay@kumarsons.com").gstin("27AABCK9876E1Z1")
            .address("456, Market Street, Gandhi Chowk")
            .city("Mumbai").state("Maharashtra").pincode("400002")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(new BigDecimal("86000.00"))
            .creditLimit(new BigDecimal("300000.00"))
            .paymentTerms("Net 30 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(3L).name("Gupta Enterprises").type("CUSTOMER")
            .contactPerson("Amit Gupta").phone("9876543212")
            .email("amit@guptaenterprises.com").gstin("27AABCG5678H1Z3")
            .address("789, Commercial Complex, Station Road")
            .city("Mumbai").state("Maharashtra").pincode("400003")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(new BigDecimal("28500.00"))
            .creditLimit(new BigDecimal("200000.00"))
            .paymentTerms("Net 15 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(4L).name("Verma Trading Co").type("CUSTOMER")
            .contactPerson("Suresh Verma").phone("9876543213")
            .email("suresh@vermatrading.com").gstin("27AABCV4321D1Z7")
            .address("321, Trade Center, MG Road")
            .city("Mumbai").state("Maharashtra").pincode("400004")
            .openingBalance(new BigDecimal("15000.00"))
            .currentBalance(new BigDecimal("55000.00"))
            .creditLimit(new BigDecimal("400000.00"))
            .paymentTerms("Net 30 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(5L).name("Singh Brothers").type("CUSTOMER")
            .contactPerson("Harpreet Singh").phone("9876543214")
            .email("harpreet@singhbrothers.com").gstin("27AABCS7890P1Z9")
            .address("654, Business Park, Link Road")
            .city("Mumbai").state("Maharashtra").pincode("400005")
            .openingBalance(new BigDecimal("25000.00"))
            .currentBalance(new BigDecimal("25000.00"))
            .creditLimit(new BigDecimal("600000.00"))
            .paymentTerms("Net 45 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(6L).name("ABC Suppliers Ltd").type("SUPPLIER")
            .contactPerson("Ramesh Patel").phone("9876543215")
            .email("ramesh@abcsuppliers.com").gstin("27AABCA1234M1Z5")
            .address("Plot 12, Industrial Estate, Phase 1")
            .city("Mumbai").state("Maharashtra").pincode("400006")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(BigDecimal.ZERO)
            .creditLimit(new BigDecimal("1000000.00"))
            .paymentTerms("Net 15 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(7L).name("XYZ Trading Company").type("SUPPLIER")
            .contactPerson("Dinesh Shah").phone("9876543216")
            .email("dinesh@xyztrading.com").gstin("27AABCX5678N1Z1")
            .address("Building A, Wholesale Market, Sector 5")
            .city("Mumbai").state("Maharashtra").pincode("400007")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(new BigDecimal("77000.00"))
            .creditLimit(new BigDecimal("800000.00"))
            .paymentTerms("Net 30 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(8L).name("Global Imports Pvt Ltd").type("SUPPLIER")
            .contactPerson("Anil Mehta").phone("9876543217")
            .email("anil@globalimports.com").gstin("27AABCG9012P1Z3")
            .address("Tower B, Import Center, Andheri East")
            .city("Mumbai").state("Maharashtra").pincode("400008")
            .openingBalance(new BigDecimal("50000.00"))
            .currentBalance(new BigDecimal("176400.00"))
            .creditLimit(new BigDecimal("1500000.00"))
            .paymentTerms("Net 45 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(9L).name("National Distributors").type("SUPPLIER")
            .contactPerson("Kiran Joshi").phone("9876543218")
            .email("kiran@nationaldist.com").gstin("27AABCN3456Q1Z7")
            .address("Warehouse 7, Logistics Park, Bhiwandi")
            .city("Thane").state("Maharashtra").pincode("421302")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(BigDecimal.ZERO)
            .creditLimit(new BigDecimal("700000.00"))
            .paymentTerms("Net 30 days")
            .active(true)
            .build());
        
        parties.add(PartyDto.builder()
            .id(10L).name("Premium Wholesale Mart").type("SUPPLIER")
            .contactPerson("Deepak Reddy").phone("9876543219")
            .email("deepak@premiumwholesale.com").gstin("27AABCP7890R1Z9")
            .address("Shop 45-48, APMC Market, Vashi")
            .city("Navi Mumbai").state("Maharashtra").pincode("400703")
            .openingBalance(BigDecimal.ZERO)
            .currentBalance(BigDecimal.ZERO)
            .creditLimit(new BigDecimal("900000.00"))
            .paymentTerms("Net 15 days")
            .active(true)
            .build());
        
        return parties;
    }
}
