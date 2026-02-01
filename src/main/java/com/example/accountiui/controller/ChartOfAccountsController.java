package com.example.accountiui.controller;

import com.example.accountiui.dto.AccountDto;
import com.example.accountiui.dto.TransactionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
public class ChartOfAccountsController {

    @GetMapping("/chart-of-accounts")
    public String chartOfAccounts(Model model) {
        List<AccountDto> accounts = createDummyAccounts();
        
        Map<String, List<AccountDto>> accountsByType = accounts.stream()
            .collect(Collectors.groupingBy(AccountDto::getType));
        
        model.addAttribute("accountsByType", accountsByType);
        model.addAttribute("accounts", accounts);
        
        return "accounts/chart-of-accounts";
    }

    @GetMapping("/general-ledger")
    public String generalLedger(@RequestParam(required = false) Long accountId, Model model) {
        List<AccountDto> accounts = createDummyAccounts();
        model.addAttribute("accounts", accounts);
        
        if (accountId != null) {
            AccountDto selectedAccount = accounts.stream()
                .filter(a -> a.getId().equals(accountId))
                .findFirst()
                .orElse(accounts.get(0));
            
            model.addAttribute("selectedAccount", selectedAccount);
            model.addAttribute("transactions", createDummyTransactions(selectedAccount));
        }
        
        return "accounts/general-ledger";
    }

    private List<AccountDto> createDummyAccounts() {
        List<AccountDto> accounts = new ArrayList<>();
        
        // Assets
        accounts.add(AccountDto.builder().id(1L).code("1001").name("Cash in Hand").type("ASSET")
            .category("Current Asset").currentBalance(new BigDecimal("125000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(2L).code("1002").name("Bank - HDFC Current Account").type("ASSET")
            .category("Current Asset").currentBalance(new BigDecimal("456780.50")).active(true).build());
        accounts.add(AccountDto.builder().id(3L).code("1003").name("Accounts Receivable").type("ASSET")
            .category("Current Asset").currentBalance(new BigDecimal("234560.00")).active(true).build());
        accounts.add(AccountDto.builder().id(4L).code("1004").name("Inventory - Stock").type("ASSET")
            .category("Current Asset").currentBalance(new BigDecimal("567890.00")).active(true).build());
        accounts.add(AccountDto.builder().id(5L).code("1100").name("Office Equipment").type("ASSET")
            .category("Fixed Asset").currentBalance(new BigDecimal("350000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(6L).code("1101").name("Furniture & Fixtures").type("ASSET")
            .category("Fixed Asset").currentBalance(new BigDecimal("185000.00")).active(true).build());
        
        // Liabilities
        accounts.add(AccountDto.builder().id(7L).code("2001").name("Accounts Payable").type("LIABILITY")
            .category("Current Liability").currentBalance(new BigDecimal("123450.00")).active(true).build());
        accounts.add(AccountDto.builder().id(8L).code("2002").name("GST Payable").type("LIABILITY")
            .category("Current Liability").currentBalance(new BigDecimal("45670.00")).active(true).build());
        accounts.add(AccountDto.builder().id(9L).code("2003").name("TDS Payable").type("LIABILITY")
            .category("Current Liability").currentBalance(new BigDecimal("12300.00")).active(true).build());
        accounts.add(AccountDto.builder().id(10L).code("2100").name("Long Term Loan").type("LIABILITY")
            .category("Long Term Liability").currentBalance(new BigDecimal("500000.00")).active(true).build());
        
        // Income
        accounts.add(AccountDto.builder().id(11L).code("3001").name("Sales - Products").type("INCOME")
            .category("Operating Income").currentBalance(new BigDecimal("850000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(12L).code("3002").name("Sales - Services").type("INCOME")
            .category("Operating Income").currentBalance(new BigDecimal("320000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(13L).code("3100").name("Other Income").type("INCOME")
            .category("Non-Operating Income").currentBalance(new BigDecimal("25000.00")).active(true).build());
        
        // Expenses
        accounts.add(AccountDto.builder().id(14L).code("4001").name("Purchase - Raw Materials").type("EXPENSE")
            .category("Direct Expense").currentBalance(new BigDecimal("450000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(15L).code("4100").name("Rent Expense").type("EXPENSE")
            .category("Indirect Expense").currentBalance(new BigDecimal("75000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(16L).code("4101").name("Salary & Wages").type("EXPENSE")
            .category("Indirect Expense").currentBalance(new BigDecimal("280000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(17L).code("4102").name("Electricity Expense").type("EXPENSE")
            .category("Indirect Expense").currentBalance(new BigDecimal("15000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(18L).code("4103").name("Telephone Expense").type("EXPENSE")
            .category("Indirect Expense").currentBalance(new BigDecimal("8500.00")).active(true).build());
        
        // Equity
        accounts.add(AccountDto.builder().id(19L).code("5001").name("Owner's Capital").type("EQUITY")
            .category("Capital").currentBalance(new BigDecimal("1500000.00")).active(true).build());
        accounts.add(AccountDto.builder().id(20L).code("5002").name("Retained Earnings").type("EQUITY")
            .category("Reserves").currentBalance(new BigDecimal("245000.00")).active(true).build());
        
        return accounts;
    }

    private List<TransactionDto> createDummyTransactions(AccountDto account) {
        List<TransactionDto> transactions = new ArrayList<>();
        BigDecimal balance = account.getOpeningBalance() != null ? account.getOpeningBalance() : BigDecimal.ZERO;
        
        transactions.add(TransactionDto.builder().id(1L)
            .date(LocalDate.now().minusDays(30))
            .voucherNumber("OPN-001").voucherType("JOURNAL")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Opening Balance")
            .debit(balance).credit(BigDecimal.ZERO).runningBalance(balance)
            .build());
        
        balance = balance.add(new BigDecimal("50000.00"));
        transactions.add(TransactionDto.builder().id(2L)
            .date(LocalDate.now().minusDays(25))
            .voucherNumber("RCT-001").voucherType("RECEIPT")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Payment received from Sharma Traders")
            .debit(new BigDecimal("50000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance).referenceNumber("INV-001")
            .build());
        
        balance = balance.subtract(new BigDecimal("25000.00"));
        transactions.add(TransactionDto.builder().id(3L)
            .date(LocalDate.now().minusDays(20))
            .voucherNumber("PAY-001").voucherType("PAYMENT")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Payment to ABC Suppliers")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("25000.00"))
            .runningBalance(balance).referenceNumber("PUR-001")
            .build());
        
        balance = balance.add(new BigDecimal("75000.00"));
        transactions.add(TransactionDto.builder().id(4L)
            .date(LocalDate.now().minusDays(15))
            .voucherNumber("RCT-002").voucherType("RECEIPT")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Sales revenue - Kumar & Sons")
            .debit(new BigDecimal("75000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance).referenceNumber("INV-002")
            .build());
        
        balance = balance.subtract(new BigDecimal("15000.00"));
        transactions.add(TransactionDto.builder().id(5L)
            .date(LocalDate.now().minusDays(10))
            .voucherNumber("PAY-002").voucherType("PAYMENT")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Rent payment for office")
            .debit(BigDecimal.ZERO).credit(new BigDecimal("15000.00"))
            .runningBalance(balance).description("Monthly rent")
            .build());
        
        balance = balance.add(new BigDecimal("45000.00"));
        transactions.add(TransactionDto.builder().id(6L)
            .date(LocalDate.now().minusDays(5))
            .voucherNumber("RCT-003").voucherType("RECEIPT")
            .accountId(account.getId()).accountName(account.getName())
            .particulars("Payment from Gupta Enterprises")
            .debit(new BigDecimal("45000.00")).credit(BigDecimal.ZERO)
            .runningBalance(balance).referenceNumber("INV-003")
            .build());
        
        return transactions;
    }
}
