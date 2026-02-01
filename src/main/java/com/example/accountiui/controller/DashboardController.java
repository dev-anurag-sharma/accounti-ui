package com.example.accountiui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        // Financial Stats (Dummy Data)
        Map<String, BigDecimal> stats = new HashMap<>();
        stats.put("cashBalance", new BigDecimal("125000.00"));
        stats.put("bankBalance", new BigDecimal("456780.50"));
        stats.put("todaySales", new BigDecimal("18500.00"));
        stats.put("receivables", new BigDecimal("234560.00"));
        stats.put("payables", new BigDecimal("123450.00"));
        stats.put("profitLoss", new BigDecimal("45000.00"));
        
        model.addAttribute("stats", stats);
        
        // Recent Invoices (Dummy Data)
        List<Map<String, Object>> recentInvoices = new ArrayList<>();
        recentInvoices.add(createInvoice("INV-001", "Sharma Traders", new BigDecimal("15000.00"), "PAID", LocalDate.now()));
        recentInvoices.add(createInvoice("INV-002", "Kumar & Sons", new BigDecimal("25600.00"), "SENT", LocalDate.now().minusDays(1)));
        recentInvoices.add(createInvoice("INV-003", "Gupta Enterprises", new BigDecimal("8500.00"), "OVERDUE", LocalDate.now().minusDays(15)));
        recentInvoices.add(createInvoice("INV-004", "Verma Trading Co", new BigDecimal("12000.00"), "DRAFT", LocalDate.now()));
        recentInvoices.add(createInvoice("INV-005", "Singh Brothers", new BigDecimal("34500.00"), "PAID", LocalDate.now().minusDays(2)));
        
        model.addAttribute("recentInvoices", recentInvoices);
        
        // Recent Purchases (Dummy Data)
        List<Map<String, Object>> recentPurchases = new ArrayList<>();
        recentPurchases.add(createPurchase("PUR-001", "ABC Suppliers", new BigDecimal("45000.00"), "PAID", LocalDate.now()));
        recentPurchases.add(createPurchase("PUR-002", "XYZ Trading", new BigDecimal("23000.00"), "RECEIVED", LocalDate.now().minusDays(1)));
        recentPurchases.add(createPurchase("PUR-003", "Global Imports", new BigDecimal("67800.00"), "RECEIVED", LocalDate.now().minusDays(3)));
        
        model.addAttribute("recentPurchases", recentPurchases);
        
        return "dashboard/index";
    }
    
    private Map<String, Object> createInvoice(String number, String customer, BigDecimal amount, String status, LocalDate date) {
        Map<String, Object> invoice = new HashMap<>();
        invoice.put("number", number);
        invoice.put("customer", customer);
        invoice.put("amount", amount);
        invoice.put("status", status);
        invoice.put("date", date);
        return invoice;
    }
    
    private Map<String, Object> createPurchase(String number, String supplier, BigDecimal amount, String status, LocalDate date) {
        Map<String, Object> purchase = new HashMap<>();
        purchase.put("number", number);
        purchase.put("supplier", supplier);
        purchase.put("amount", amount);
        purchase.put("status", status);
        purchase.put("date", date);
        return purchase;
    }
}
