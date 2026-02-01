package com.example.accountiui.controller;

import com.example.accountiui.dto.InventoryDto;
import com.example.accountiui.dto.TransactionDto;
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
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", createDummyInventory());
        return "inventory/inventory-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        InventoryDto product = createDummyInventory().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(createDummyInventory().get(0));
        
        model.addAttribute("product", product);
        model.addAttribute("stockLedger", createStockLedger(product));
        return "inventory/inventory-detail";
    }

    private List<InventoryDto> createDummyInventory() {
        List<InventoryDto> products = new ArrayList<>();
        
        products.add(InventoryDto.builder()
            .id(1L).productCode("PROD-001").productName("Premium Basmati Rice 25kg")
            .category("Food Grains").description("Premium quality long grain basmati rice")
            .unit("BAG").purchaseRate(new BigDecimal("950.00"))
            .sellingRate(new BigDecimal("1200.00"))
            .openingStock(new BigDecimal("100"))
            .currentStock(new BigDecimal("145"))
            .reorderLevel(new BigDecimal("50"))
            .stockValue(new BigDecimal("137750.00"))
            .hsnCode("1006").gstRate(new BigDecimal("5.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(2L).productCode("PROD-002").productName("Refined Sunflower Oil 5L")
            .category("Edible Oils").description("Refined sunflower cooking oil")
            .unit("BOT").purchaseRate(new BigDecimal("520.00"))
            .sellingRate(new BigDecimal("650.00"))
            .openingStock(new BigDecimal("80"))
            .currentStock(new BigDecimal("95"))
            .reorderLevel(new BigDecimal("30"))
            .stockValue(new BigDecimal("49400.00"))
            .hsnCode("1512").gstRate(new BigDecimal("18.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(3L).productCode("PROD-003").productName("Premium Wheat Flour 10kg")
            .category("Food Grains").description("100% whole wheat chakki atta")
            .unit("BAG").purchaseRate(new BigDecimal("220.00"))
            .sellingRate(new BigDecimal("280.00"))
            .openingStock(new BigDecimal("150"))
            .currentStock(new BigDecimal("185"))
            .reorderLevel(new BigDecimal("60"))
            .stockValue(new BigDecimal("40700.00"))
            .hsnCode("1101").gstRate(new BigDecimal("5.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(4L).productCode("PROD-004").productName("Red Toor Dal 1kg")
            .category("Pulses").description("Premium quality split pigeon peas")
            .unit("PKT").purchaseRate(new BigDecimal("95.00"))
            .sellingRate(new BigDecimal("125.00"))
            .openingStock(new BigDecimal("200"))
            .currentStock(new BigDecimal("165"))
            .reorderLevel(new BigDecimal("80"))
            .stockValue(new BigDecimal("15675.00"))
            .hsnCode("0713").gstRate(new BigDecimal("5.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(5L).productCode("PROD-005").productName("Sugar 1kg")
            .category("Sugar & Jaggery").description("Sulphur-free refined white sugar")
            .unit("PKT").purchaseRate(new BigDecimal("38.00"))
            .sellingRate(new BigDecimal("48.00"))
            .openingStock(new BigDecimal("300"))
            .currentStock(new BigDecimal("245"))
            .reorderLevel(new BigDecimal("100"))
            .stockValue(new BigDecimal("9310.00"))
            .hsnCode("1701").gstRate(BigDecimal.ZERO)
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(6L).productCode("PROD-006").productName("Tea Powder 500g")
            .category("Beverages").description("Premium CTC blend tea powder")
            .unit("PKT").purchaseRate(new BigDecimal("180.00"))
            .sellingRate(new BigDecimal("240.00"))
            .openingStock(new BigDecimal("120"))
            .currentStock(new BigDecimal("98"))
            .reorderLevel(new BigDecimal("40"))
            .stockValue(new BigDecimal("17640.00"))
            .hsnCode("0902").gstRate(new BigDecimal("5.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(7L).productCode("PROD-007").productName("Detergent Powder 1kg")
            .category("Household").description("Multi-purpose washing powder")
            .unit("PKT").purchaseRate(new BigDecimal("125.00"))
            .sellingRate(new BigDecimal("165.00"))
            .openingStock(new BigDecimal("75"))
            .currentStock(new BigDecimal("52"))
            .reorderLevel(new BigDecimal("25"))
            .stockValue(new BigDecimal("6500.00"))
            .hsnCode("3402").gstRate(new BigDecimal("18.00"))
            .active(true)
            .build());
        
        products.add(InventoryDto.builder()
            .id(8L).productCode("PROD-008").productName("Mustard Oil 5L")
            .category("Edible Oils").description("Pure cold-pressed mustard oil")
            .unit("BOT").purchaseRate(new BigDecimal("580.00"))
            .sellingRate(new BigDecimal("720.00"))
            .openingStock(new BigDecimal("60"))
            .currentStock(new BigDecimal("48"))
            .reorderLevel(new BigDecimal("20"))
            .stockValue(new BigDecimal("27840.00"))
            .hsnCode("1514").gstRate(new BigDecimal("5.00"))
            .active(true)
            .build());
        
        return products;
    }

    private List<TransactionDto> createStockLedger(InventoryDto product) {
        List<TransactionDto> ledger = new ArrayList<>();
        
        BigDecimal balance = product.getOpeningStock();
        
        ledger.add(TransactionDto.builder()
            .id(1L).date(LocalDate.now().minusDays(30))
            .voucherNumber("OPN-001").voucherType("OPENING")
            .particulars("Opening Stock")
            .debit(balance).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("50"));
        ledger.add(TransactionDto.builder()
            .id(2L).date(LocalDate.now().minusDays(25))
            .voucherNumber("PUR-001").voucherType("PURCHASE")
            .particulars("Purchase from ABC Suppliers")
            .debit(new BigDecimal("50")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.subtract(new BigDecimal("30"));
        ledger.add(TransactionDto.builder()
            .id(3L).date(LocalDate.now().minusDays(20))
            .voucherNumber("INV-001").voucherType("SALES")
            .particulars("Sales to Sharma Traders")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("30"))
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("40"));
        ledger.add(TransactionDto.builder()
            .id(4L).date(LocalDate.now().minusDays(15))
            .voucherNumber("PUR-002").voucherType("PURCHASE")
            .particulars("Purchase from XYZ Trading")
            .debit(new BigDecimal("40")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.subtract(new BigDecimal("25"));
        ledger.add(TransactionDto.builder()
            .id(5L).date(LocalDate.now().minusDays(10))
            .voucherNumber("INV-002").voucherType("SALES")
            .particulars("Sales to Kumar & Sons")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("25"))
            .runningBalance(balance)
            .build());
        
        return ledger;
    }
}
