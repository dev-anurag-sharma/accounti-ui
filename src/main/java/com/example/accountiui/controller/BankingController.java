package com.example.accountiui.controller;

import com.example.accountiui.dto.TransactionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/banking")
public class BankingController {

    @GetMapping("/cash-book")
    public String cashBook(Model model) {
        model.addAttribute("transactions", createCashTransactions());
        model.addAttribute("openingBalance", new BigDecimal("100000.00"));
        model.addAttribute("closingBalance", new BigDecimal("125000.00"));
        model.addAttribute("totalReceipts", new BigDecimal("117500.00"));
        model.addAttribute("totalPayments", new BigDecimal("50500.00"));
        return "banking/cash-book";
    }

    @GetMapping("/bank-book")
    public String bankBook(Model model) {
        model.addAttribute("transactions", createBankTransactions());
        model.addAttribute("openingBalance", new BigDecimal("400000.00"));
        model.addAttribute("closingBalance", new BigDecimal("456780.50"));
        model.addAttribute("totalReceipts", new BigDecimal("310000.00"));
        model.addAttribute("totalPayments", new BigDecimal("156719.50"));
        return "banking/bank-book";
    }

    @PostMapping("/receipt/save")
    public String saveReceipt(TransactionDto transaction, RedirectAttributes redirectAttributes) {
        // In a real application, this would save to database
        // For now, just redirect with success message
        redirectAttributes.addFlashAttribute("successMessage", "Receipt added successfully!");
        
        // Determine redirect URL based on account type (cash or bank)
        String redirectUrl = "cash".equalsIgnoreCase(transaction.getAccountName()) 
            ? "redirect:/banking/cash-book" 
            : "redirect:/banking/bank-book";
        
        return redirectUrl;
    }

    @PostMapping("/payment/save")
    public String savePayment(TransactionDto transaction, RedirectAttributes redirectAttributes) {
        // In a real application, this would save to database
        // For now, just redirect with success message
        redirectAttributes.addFlashAttribute("successMessage", "Payment added successfully!");
        
        // Determine redirect URL based on account type (cash or bank)
        String redirectUrl = "cash".equalsIgnoreCase(transaction.getAccountName()) 
            ? "redirect:/banking/cash-book" 
            : "redirect:/banking/bank-book";
        
        return redirectUrl;
    }

    private List<TransactionDto> createCashTransactions() {
        List<TransactionDto> transactions = new ArrayList<>();
        BigDecimal balance = new BigDecimal("100000.00");
        
        transactions.add(TransactionDto.builder()
            .id(1L).date(LocalDate.now().minusDays(30))
            .voucherNumber("OPN-001").voucherType("OPENING")
            .particulars("Opening Balance")
            .debit(balance).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("50000.00"));
        transactions.add(TransactionDto.builder()
            .id(2L).date(LocalDate.now().minusDays(28))
            .voucherNumber("RCT-001").voucherType("RECEIPT")
            .particulars("Cash received from Sharma Traders")
            .debit(new BigDecimal("50000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .referenceNumber("INV-001")
            .build());
        
        balance = balance.subtract(new BigDecimal("15000.00"));
        transactions.add(TransactionDto.builder()
            .id(3L).date(LocalDate.now().minusDays(25))
            .voucherNumber("PAY-001").voucherType("PAYMENT")
            .particulars("Rent payment")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("15000.00"))
            .runningBalance(balance)
            .description("Monthly office rent")
            .build());
        
        balance = balance.add(new BigDecimal("25000.00"));
        transactions.add(TransactionDto.builder()
            .id(4L).date(LocalDate.now().minusDays(22))
            .voucherNumber("RCT-002").voucherType("RECEIPT")
            .particulars("Cash sales")
            .debit(new BigDecimal("25000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.subtract(new BigDecimal("8000.00"));
        transactions.add(TransactionDto.builder()
            .id(5L).date(LocalDate.now().minusDays(20))
            .voucherNumber("PAY-002").voucherType("PAYMENT")
            .particulars("Electricity bill payment")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("8000.00"))
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("18500.00"));
        transactions.add(TransactionDto.builder()
            .id(6L).date(LocalDate.now().minusDays(18))
            .voucherNumber("RCT-003").voucherType("RECEIPT")
            .particulars("Cash received from Kumar & Sons")
            .debit(new BigDecimal("18500.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .referenceNumber("INV-002")
            .build());
        
        balance = balance.subtract(new BigDecimal("5000.00"));
        transactions.add(TransactionDto.builder()
            .id(7L).date(LocalDate.now().minusDays(15))
            .voucherNumber("PAY-003").voucherType("PAYMENT")
            .particulars("Petty expenses")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("5000.00"))
            .runningBalance(balance)
            .build());
        
        balance = balance.subtract(new BigDecimal("20000.00"));
        transactions.add(TransactionDto.builder()
            .id(8L).date(LocalDate.now().minusDays(12))
            .voucherNumber("CNT-001").voucherType("CONTRA")
            .particulars("Deposited to bank")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("20000.00"))
            .runningBalance(balance)
            .description("Transferred to HDFC Current Account")
            .build());
        
        balance = balance.add(new BigDecimal("12000.00"));
        transactions.add(TransactionDto.builder()
            .id(9L).date(LocalDate.now().minusDays(8))
            .voucherNumber("RCT-004").voucherType("RECEIPT")
            .particulars("Cash sales")
            .debit(new BigDecimal("12000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.subtract(new BigDecimal("2500.00"));
        transactions.add(TransactionDto.builder()
            .id(10L).date(LocalDate.now().minusDays(5))
            .voucherNumber("PAY-004").voucherType("PAYMENT")
            .particulars("Office supplies")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("2500.00"))
            .runningBalance(balance)
            .build());
        
        return transactions;
    }

    private List<TransactionDto> createBankTransactions() {
        List<TransactionDto> transactions = new ArrayList<>();
        BigDecimal balance = new BigDecimal("400000.00");
        
        transactions.add(TransactionDto.builder()
            .id(1L).date(LocalDate.now().minusDays(30))
            .voucherNumber("OPN-001").voucherType("OPENING")
            .particulars("Opening Balance - HDFC Bank")
            .debit(balance).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("75000.00"));
        transactions.add(TransactionDto.builder()
            .id(2L).date(LocalDate.now().minusDays(27))
            .voucherNumber("RCT-001").voucherType("RECEIPT")
            .particulars("NEFT received from Gupta Enterprises")
            .debit(new BigDecimal("75000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .referenceNumber("INV-003")
            .build());
        
        balance = balance.subtract(new BigDecimal("45000.00"));
        transactions.add(TransactionDto.builder()
            .id(3L).date(LocalDate.now().minusDays(24))
            .voucherNumber("PAY-001").voucherType("PAYMENT")
            .particulars("NEFT to ABC Suppliers")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("45000.00"))
            .runningBalance(balance)
            .referenceNumber("PUR-001")
            .build());
        
        balance = balance.add(new BigDecimal("20000.00"));
        transactions.add(TransactionDto.builder()
            .id(4L).date(LocalDate.now().minusDays(22))
            .voucherNumber("CNT-001").voucherType("CONTRA")
            .particulars("Cash deposited")
            .debit(new BigDecimal("20000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("120000.00"));
        transactions.add(TransactionDto.builder()
            .id(5L).date(LocalDate.now().minusDays(18))
            .voucherNumber("RCT-002").voucherType("RECEIPT")
            .particulars("IMPS from Singh Brothers")
            .debit(new BigDecimal("120000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .referenceNumber("INV-005")
            .build());
        
        balance = balance.subtract(new BigDecimal("80000.00"));
        transactions.add(TransactionDto.builder()
            .id(6L).date(LocalDate.now().minusDays(15))
            .voucherNumber("PAY-002").voucherType("PAYMENT")
            .particulars("Salary payment via bank")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("80000.00"))
            .runningBalance(balance)
            .description("Staff salary for the month")
            .build());
        
        balance = balance.subtract(new BigDecimal("35000.00"));
        transactions.add(TransactionDto.builder()
            .id(7L).date(LocalDate.now().minusDays(12))
            .voucherNumber("PAY-003").voucherType("PAYMENT")
            .particulars("RTGS to XYZ Trading")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("35000.00"))
            .runningBalance(balance)
            .referenceNumber("PUR-002")
            .build());
        
        balance = balance.add(new BigDecimal("95000.00"));
        transactions.add(TransactionDto.builder()
            .id(8L).date(LocalDate.now().minusDays(8))
            .voucherNumber("RCT-003").voucherType("RECEIPT")
            .particulars("NEFT from Verma Trading Co")
            .debit(new BigDecimal("95000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance)
            .referenceNumber("INV-004")
            .build());
        
        balance = balance.subtract(new BigDecimal("1500.00"));
        transactions.add(TransactionDto.builder()
            .id(9L).date(LocalDate.now().minusDays(5))
            .voucherNumber("PAY-004").voucherType("PAYMENT")
            .particulars("Bank charges")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("1500.00"))
            .runningBalance(balance)
            .description("Quarterly bank charges")
            .build());
        
        balance = balance.subtract(new BigDecimal("15219.50"));
        transactions.add(TransactionDto.builder()
            .id(10L).date(LocalDate.now().minusDays(2))
            .voucherNumber("PAY-005").voucherType("PAYMENT")
            .particulars("GST payment online")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("15219.50"))
            .runningBalance(balance)
            .description("GST payment for last month")
            .build());
        
        return transactions;
    }
}
