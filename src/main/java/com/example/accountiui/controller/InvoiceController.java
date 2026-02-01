package com.example.accountiui.controller;

import com.example.accountiui.dto.InvoiceDto;
import com.example.accountiui.dto.InvoiceItemDto;
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
@RequestMapping("/invoice")
public class InvoiceController {

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("invoices", createDummyInvoices());
        return "invoice/invoice-list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("invoice", new InvoiceDto());
        return "invoice/invoice-create";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        InvoiceDto invoice = createDummyInvoices().stream()
            .filter(i -> i.getId().equals(id))
            .findFirst()
            .orElse(createDummyInvoices().get(0));
        
        model.addAttribute("invoice", invoice);
        return "invoice/invoice-view";
    }

    private List<InvoiceDto> createDummyInvoices() {
        List<InvoiceDto> invoices = new ArrayList<>();
        
        invoices.add(InvoiceDto.builder()
            .id(1L).invoiceNumber("INV-2024-001").type("TAX_INVOICE")
            .invoiceDate(LocalDate.now()).dueDate(LocalDate.now().plusDays(30))
            .customerId(1L).customerName("Sharma Traders")
            .customerGstin("27AABCS1234F1Z5")
            .subtotal(new BigDecimal("42372.88"))
            .cgst(new BigDecimal("3813.56"))
            .sgst(new BigDecimal("3813.56"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("50000.00"))
            .paidAmount(new BigDecimal("50000.00"))
            .dueAmount(BigDecimal.ZERO)
            .status("PAID")
            .notes("Thank you for your business!")
            .items(createDummyInvoiceItems())
            .build());
        
        invoices.add(InvoiceDto.builder()
            .id(2L).invoiceNumber("INV-2024-002").type("TAX_INVOICE")
            .invoiceDate(LocalDate.now().minusDays(1)).dueDate(LocalDate.now().plusDays(29))
            .customerId(2L).customerName("Kumar & Sons")
            .customerGstin("27AABCK9876E1Z1")
            .subtotal(new BigDecimal("72881.36"))
            .cgst(new BigDecimal("6559.32"))
            .sgst(new BigDecimal("6559.32"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("86000.00"))
            .paidAmount(BigDecimal.ZERO)
            .dueAmount(new BigDecimal("86000.00"))
            .status("SENT")
            .notes("Payment terms: Net 30 days")
            .items(createDummyInvoiceItems())
            .build());
        
        invoices.add(InvoiceDto.builder()
            .id(3L).invoiceNumber("INV-2024-003").type("TAX_INVOICE")
            .invoiceDate(LocalDate.now().minusDays(15)).dueDate(LocalDate.now().minusDays(1))
            .customerId(3L).customerName("Gupta Enterprises")
            .customerGstin("27AABCG5678H1Z3")
            .subtotal(new BigDecimal("24152.54"))
            .cgst(new BigDecimal("2173.73"))
            .sgst(new BigDecimal("2173.73"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("28500.00"))
            .paidAmount(BigDecimal.ZERO)
            .dueAmount(new BigDecimal("28500.00"))
            .status("OVERDUE")
            .notes("Payment overdue - please clear dues")
            .items(createDummyInvoiceItems())
            .build());
        
        invoices.add(InvoiceDto.builder()
            .id(4L).invoiceNumber("INV-2024-004").type("TAX_INVOICE")
            .invoiceDate(LocalDate.now()).dueDate(LocalDate.now().plusDays(15))
            .customerId(4L).customerName("Verma Trading Co")
            .customerGstin("27AABCV4321D1Z7")
            .subtotal(new BigDecimal("33898.31"))
            .cgst(new BigDecimal("3050.85"))
            .sgst(new BigDecimal("3050.85"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("40000.00"))
            .paidAmount(BigDecimal.ZERO)
            .dueAmount(new BigDecimal("40000.00"))
            .status("DRAFT")
            .notes("Draft invoice - not yet sent")
            .items(createDummyInvoiceItems())
            .build());
        
        invoices.add(InvoiceDto.builder()
            .id(5L).invoiceNumber("INV-2024-005").type("TAX_INVOICE")
            .invoiceDate(LocalDate.now().minusDays(2)).dueDate(LocalDate.now().plusDays(28))
            .customerId(5L).customerName("Singh Brothers")
            .customerGstin("27AABCS7890P1Z9")
            .subtotal(new BigDecimal("97881.36"))
            .cgst(new BigDecimal("8809.32"))
            .sgst(new BigDecimal("8809.32"))
            .igst(BigDecimal.ZERO)
            .totalAmount(new BigDecimal("115500.00"))
            .paidAmount(new BigDecimal("115500.00"))
            .dueAmount(BigDecimal.ZERO)
            .status("PAID")
            .notes("Payment received - Thank you!")
            .items(createDummyInvoiceItems())
            .build());
        
        return invoices;
    }

    private List<InvoiceItemDto> createDummyInvoiceItems() {
        List<InvoiceItemDto> items = new ArrayList<>();
        
        items.add(InvoiceItemDto.builder()
            .id(1L).productId(1L).productName("Premium Basmati Rice 25kg")
            .hsnCode("1006").quantity(new BigDecimal("20"))
            .unit("BAG").rate(new BigDecimal("1200.00"))
            .amount(new BigDecimal("24000.00"))
            .gstRate(new BigDecimal("5.00"))
            .cgst(new BigDecimal("600.00"))
            .sgst(new BigDecimal("600.00"))
            .igst(BigDecimal.ZERO)
            .total(new BigDecimal("25200.00"))
            .build());
        
        items.add(InvoiceItemDto.builder()
            .id(2L).productId(2L).productName("Refined Sunflower Oil 5L")
            .hsnCode("1512").quantity(new BigDecimal("15"))
            .unit("BOT").rate(new BigDecimal("650.00"))
            .amount(new BigDecimal("9750.00"))
            .gstRate(new BigDecimal("18.00"))
            .cgst(new BigDecimal("877.50"))
            .sgst(new BigDecimal("877.50"))
            .igst(BigDecimal.ZERO)
            .total(new BigDecimal("11505.00"))
            .build());
        
        items.add(InvoiceItemDto.builder()
            .id(3L).productId(3L).productName("Premium Wheat Flour 10kg")
            .hsnCode("1101").quantity(new BigDecimal("30"))
            .unit("BAG").rate(new BigDecimal("280.00"))
            .amount(new BigDecimal("8400.00"))
            .gstRate(new BigDecimal("5.00"))
            .cgst(new BigDecimal("210.00"))
            .sgst(new BigDecimal("210.00"))
            .igst(BigDecimal.ZERO)
            .total(new BigDecimal("8820.00"))
            .build());
        
        return items;
    }
}
