package com.example.accountiui.controller;

import com.example.accountiui.dto.PurchaseDto;
import com.example.accountiui.dto.PurchaseItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("purchases", createDummyPurchases());
        return "purchase/purchase-list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("purchase", new PurchaseDto());
        return "purchase/purchase-create";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        PurchaseDto purchase = createDummyPurchases().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(createDummyPurchases().get(0));
        
        model.addAttribute("purchase", purchase);
        return "purchase/purchase-view";
    }

    private List<PurchaseDto> createDummyPurchases() {
        List<PurchaseDto> purchases = new ArrayList<>();
        
        purchases.add(PurchaseDto.builder()
            .id(1L).purchaseNumber("PUR-2024-001")
            .purchaseDate(LocalDate.now())
            .supplierId(1L).supplierName("ABC Suppliers Ltd")
            .supplierGstin("27AABCA1234M1Z5")
            .subtotal(new BigDecimal("127118.64"))
            .cgst(new BigDecimal("11440.68"))
            .sgst(new BigDecimal("11440.68"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("150000.00"))
            .paidAmount(new BigDecimal("150000.00"))
            .dueAmount(BigDecimal.ZERO)
            .status("PAID")
            .notes("All items received in good condition")
            .items(createDummyPurchaseItems())
            .build());
        
        purchases.add(PurchaseDto.builder()
            .id(2L).purchaseNumber("PUR-2024-002")
            .purchaseDate(LocalDate.now().minusDays(1))
            .supplierId(2L).supplierName("XYZ Trading Company")
            .supplierGstin("27AABCX5678N1Z1")
            .subtotal(new BigDecimal("65254.24"))
            .cgst(new BigDecimal("5872.88"))
            .sgst(new BigDecimal("5872.88"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("77000.00"))
            .paidAmount(BigDecimal.ZERO)
            .dueAmount(new BigDecimal("77000.00"))
            .status("RECEIVED")
            .notes("Payment due in 15 days")
            .items(createDummyPurchaseItems())
            .build());
        
        purchases.add(PurchaseDto.builder()
            .id(3L).purchaseNumber("PUR-2024-003")
            .purchaseDate(LocalDate.now().minusDays(3))
            .supplierId(3L).supplierName("Global Imports Pvt Ltd")
            .supplierGstin("27AABCG9012P1Z3")
            .subtotal(new BigDecimal("191864.41"))
            .cgst(new BigDecimal("17267.80"))
            .sgst(new BigDecimal("17267.80"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("226400.00"))
            .paidAmount(new BigDecimal("100000.00"))
            .dueAmount(new BigDecimal("126400.00"))
            .status("RECEIVED")
            .notes("Partial payment made - Balance due")
            .items(createDummyPurchaseItems())
            .build());
        
        purchases.add(PurchaseDto.builder()
            .id(4L).purchaseNumber("PUR-2024-004")
            .purchaseDate(LocalDate.now().minusDays(5))
            .supplierId(4L).supplierName("National Distributors")
            .supplierGstin("27AABCN3456Q1Z7")
            .subtotal(new BigDecimal("33898.31"))
            .cgst(new BigDecimal("3050.85"))
            .sgst(new BigDecimal("3050.85"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("40000.00"))
            .paidAmount(new BigDecimal("40000.00"))
            .dueAmount(BigDecimal.ZERO)
            .status("PAID")
            .notes("Paid via NEFT")
            .items(createDummyPurchaseItems())
            .build());
        
        purchases.add(PurchaseDto.builder()
            .id(5L).purchaseNumber("PUR-2024-005")
            .purchaseDate(LocalDate.now().minusDays(7))
            .supplierId(5L).supplierName("Premium Wholesale Mart")
            .supplierGstin("27AABCP7890R1Z9")
            .subtotal(new BigDecimal("84745.76"))
            .cgst(new BigDecimal("7627.12"))
            .sgst(new BigDecimal("7627.12"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("100000.00"))
            .paidAmount(new BigDecimal("100000.00"))
            .dueAmount(BigDecimal.ZERO)
            .status("PAID")
            .notes("Quality checked and approved")
            .items(createDummyPurchaseItems())
            .build());
        
        return purchases;
    }

    private List<PurchaseItemDto> createDummyPurchaseItems() {
        List<PurchaseItemDto> items = new ArrayList<>();
        
        items.add(PurchaseItemDto.builder()
            .id(1L).productId(1L).productName("Premium Basmati Rice 25kg")
            .description("HSN: 1006").quantity(new BigDecimal("50"))
            .unit("BAG").rate(new BigDecimal("950.00"))
            .taxRate(new BigDecimal("5.00"))
            .amount(new BigDecimal("49875.00"))
            .build());
        
        items.add(PurchaseItemDto.builder()
            .id(2L).productId(2L).productName("Refined Sunflower Oil 5L")
            .description("HSN: 1512").quantity(new BigDecimal("40"))
            .unit("BOT").rate(new BigDecimal("520.00"))
            .taxRate(new BigDecimal("18.00"))
            .amount(new BigDecimal("24544.00"))
            .build());
        
        items.add(PurchaseItemDto.builder()
            .id(3L).productId(3L).productName("Premium Wheat Flour 10kg")
            .description("HSN: 1101").quantity(new BigDecimal("100"))
            .unit("BAG").rate(new BigDecimal("220.00"))
            .taxRate(new BigDecimal("5.00"))
            .amount(new BigDecimal("23100.00"))
            .build());
        
        return items;
    }
}
