package com.example.cnExpense.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cnExpense.entities.Expense;
import com.example.cnExpense.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/save/{incomeId}")
    public void saveExpenseForIncome(@PathVariable Integer incomeId, @RequestBody Expense expense){
        expenseService.saveExpenseForIncome(incomeId,expense);
    }

}
