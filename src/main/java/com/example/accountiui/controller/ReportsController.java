package com.example.accountiui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    @GetMapping("/trial-balance")
    public String trialBalance(Model model) {
        List<Map<String, Object>> accounts = new ArrayList<>();
        BigDecimal totalDebit = BigDecimal.ZERO;
        BigDecimal totalCredit = BigDecimal.ZERO;
        
        // Assets
        accounts.add(createAccount("Cash in Hand", new BigDecimal("125000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Bank - HDFC", new BigDecimal("456780.50"), BigDecimal.ZERO));
        accounts.add(createAccount("Accounts Receivable", new BigDecimal("234560.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Inventory - Stock", new BigDecimal("567890.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Office Equipment", new BigDecimal("350000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Furniture & Fixtures", new BigDecimal("185000.00"), BigDecimal.ZERO));
        
        // Expenses
        accounts.add(createAccount("Purchase - Raw Materials", new BigDecimal("450000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Rent Expense", new BigDecimal("75000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Salary & Wages", new BigDecimal("280000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Electricity Expense", new BigDecimal("15000.00"), BigDecimal.ZERO));
        accounts.add(createAccount("Telephone Expense", new BigDecimal("8500.00"), BigDecimal.ZERO));
        
        // Liabilities
        accounts.add(createAccount("Accounts Payable", BigDecimal.ZERO, new BigDecimal("123450.00")));
        accounts.add(createAccount("GST Payable", BigDecimal.ZERO, new BigDecimal("45670.00")));
        accounts.add(createAccount("TDS Payable", BigDecimal.ZERO, new BigDecimal("12300.00")));
        accounts.add(createAccount("Long Term Loan", BigDecimal.ZERO, new BigDecimal("500000.00")));
        
        // Income
        accounts.add(createAccount("Sales - Products", BigDecimal.ZERO, new BigDecimal("850000.00")));
        accounts.add(createAccount("Sales - Services", BigDecimal.ZERO, new BigDecimal("320000.00")));
        accounts.add(createAccount("Other Income", BigDecimal.ZERO, new BigDecimal("25000.00")));
        
        // Equity
        accounts.add(createAccount("Owner's Capital", BigDecimal.ZERO, new BigDecimal("1500000.00")));
        accounts.add(createAccount("Retained Earnings", BigDecimal.ZERO, new BigDecimal("245000.00")));
        
        for (Map<String, Object> account : accounts) {
            totalDebit = totalDebit.add((BigDecimal) account.get("debit"));
            totalCredit = totalCredit.add((BigDecimal) account.get("credit"));
        }
        
        model.addAttribute("accounts", accounts);
        model.addAttribute("totalDebit", totalDebit);
        model.addAttribute("totalCredit", totalCredit);
        
        return "reports/trial-balance";
    }

    @GetMapping("/profit-loss")
    public String profitLoss(Model model) {
        List<Map<String, Object>> incomeAccounts = new ArrayList<>();
        List<Map<String, Object>> expenseAccounts = new ArrayList<>();
        
        // Income
        incomeAccounts.add(createPLAccount("Sales - Products", new BigDecimal("850000.00")));
        incomeAccounts.add(createPLAccount("Sales - Services", new BigDecimal("320000.00")));
        incomeAccounts.add(createPLAccount("Other Income", new BigDecimal("25000.00")));
        
        BigDecimal totalIncome = incomeAccounts.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Expenses
        expenseAccounts.add(createPLAccount("Purchase - Raw Materials", new BigDecimal("450000.00")));
        expenseAccounts.add(createPLAccount("Rent Expense", new BigDecimal("75000.00")));
        expenseAccounts.add(createPLAccount("Salary & Wages", new BigDecimal("280000.00")));
        expenseAccounts.add(createPLAccount("Electricity Expense", new BigDecimal("15000.00")));
        expenseAccounts.add(createPLAccount("Telephone Expense", new BigDecimal("8500.00")));
        expenseAccounts.add(createPLAccount("Office Supplies", new BigDecimal("12500.00")));
        expenseAccounts.add(createPLAccount("Depreciation", new BigDecimal("25000.00")));
        expenseAccounts.add(createPLAccount("Bank Charges", new BigDecimal("3500.00")));
        
        BigDecimal totalExpenses = expenseAccounts.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal netProfit = totalIncome.subtract(totalExpenses);
        
        model.addAttribute("incomeAccounts", incomeAccounts);
        model.addAttribute("expenseAccounts", expenseAccounts);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("netProfit", netProfit);
        
        return "reports/profit-loss";
    }

    @GetMapping("/balance-sheet")
    public String balanceSheet(Model model) {
        List<Map<String, Object>> assets = new ArrayList<>();
        List<Map<String, Object>> liabilities = new ArrayList<>();
        
        // Current Assets
        Map<String, Object> currentAssets = new HashMap<>();
        List<Map<String, Object>> currentAssetItems = new ArrayList<>();
        currentAssetItems.add(createBSAccount("Cash in Hand", new BigDecimal("125000.00")));
        currentAssetItems.add(createBSAccount("Bank - HDFC", new BigDecimal("456780.50")));
        currentAssetItems.add(createBSAccount("Accounts Receivable", new BigDecimal("234560.00")));
        currentAssetItems.add(createBSAccount("Inventory", new BigDecimal("567890.00")));
        BigDecimal currentAssetsTotal = currentAssetItems.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        currentAssets.put("category", "Current Assets");
        currentAssets.put("items", currentAssetItems);
        currentAssets.put("total", currentAssetsTotal);
        assets.add(currentAssets);
        
        // Fixed Assets
        Map<String, Object> fixedAssets = new HashMap<>();
        List<Map<String, Object>> fixedAssetItems = new ArrayList<>();
        fixedAssetItems.add(createBSAccount("Office Equipment", new BigDecimal("350000.00")));
        fixedAssetItems.add(createBSAccount("Furniture & Fixtures", new BigDecimal("185000.00")));
        fixedAssetItems.add(createBSAccount("Less: Accumulated Depreciation", new BigDecimal("-75000.00")));
        BigDecimal fixedAssetsTotal = fixedAssetItems.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        fixedAssets.put("category", "Fixed Assets");
        fixedAssets.put("items", fixedAssetItems);
        fixedAssets.put("total", fixedAssetsTotal);
        assets.add(fixedAssets);
        
        BigDecimal totalAssets = currentAssetsTotal.add(fixedAssetsTotal);
        
        // Current Liabilities
        Map<String, Object> currentLiabilities = new HashMap<>();
        List<Map<String, Object>> currentLiabilityItems = new ArrayList<>();
        currentLiabilityItems.add(createBSAccount("Accounts Payable", new BigDecimal("123450.00")));
        currentLiabilityItems.add(createBSAccount("GST Payable", new BigDecimal("45670.00")));
        currentLiabilityItems.add(createBSAccount("TDS Payable", new BigDecimal("12300.00")));
        BigDecimal currentLiabilitiesTotal = currentLiabilityItems.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        currentLiabilities.put("category", "Current Liabilities");
        currentLiabilities.put("items", currentLiabilityItems);
        currentLiabilities.put("total", currentLiabilitiesTotal);
        liabilities.add(currentLiabilities);
        
        // Long Term Liabilities
        Map<String, Object> longTermLiabilities = new HashMap<>();
        List<Map<String, Object>> longTermLiabilityItems = new ArrayList<>();
        longTermLiabilityItems.add(createBSAccount("Long Term Loan", new BigDecimal("500000.00")));
        BigDecimal longTermLiabilitiesTotal = longTermLiabilityItems.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        longTermLiabilities.put("category", "Long Term Liabilities");
        longTermLiabilities.put("items", longTermLiabilityItems);
        longTermLiabilities.put("total", longTermLiabilitiesTotal);
        liabilities.add(longTermLiabilities);
        
        // Equity
        Map<String, Object> equity = new HashMap<>();
        List<Map<String, Object>> equityItems = new ArrayList<>();
        equityItems.add(createBSAccount("Owner's Capital", new BigDecimal("1500000.00")));
        equityItems.add(createBSAccount("Retained Earnings", new BigDecimal("245000.00")));
        equityItems.add(createBSAccount("Current Year Profit", new BigDecimal("325810.50")));
        BigDecimal equityTotal = equityItems.stream()
            .map(a -> (BigDecimal) a.get("amount"))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        equity.put("category", "Equity");
        equity.put("items", equityItems);
        equity.put("total", equityTotal);
        liabilities.add(equity);
        
        BigDecimal totalLiabilitiesAndEquity = currentLiabilitiesTotal.add(longTermLiabilitiesTotal).add(equityTotal);
        
        model.addAttribute("assets", assets);
        model.addAttribute("liabilities", liabilities);
        model.addAttribute("totalAssets", totalAssets);
        model.addAttribute("totalLiabilitiesAndEquity", totalLiabilitiesAndEquity);
        
        return "reports/balance-sheet";
    }

    private Map<String, Object> createAccount(String name, BigDecimal debit, BigDecimal credit) {
        Map<String, Object> account = new HashMap<>();
        account.put("name", name);
        account.put("debit", debit);
        account.put("credit", credit);
        return account;
    }

    private Map<String, Object> createPLAccount(String name, BigDecimal amount) {
        Map<String, Object> account = new HashMap<>();
        account.put("name", name);
        account.put("amount", amount);
        return account;
    }

    private Map<String, Object> createBSAccount(String name, BigDecimal amount) {
        Map<String, Object> account = new HashMap<>();
        account.put("name", name);
        account.put("amount", amount);
        return account;
    }
}
