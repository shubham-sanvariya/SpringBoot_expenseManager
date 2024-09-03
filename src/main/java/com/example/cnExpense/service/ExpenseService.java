package com.example.cnExpense.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cnExpense.DAL.ExpenseDAL;
import com.example.cnExpense.DAL.IncomeDAL;
import com.example.cnExpense.entities.Expense;
import com.example.cnExpense.entities.Income;
import com.example.cnExpense.exception.ElementAlreadyExistException;
import com.example.cnExpense.exception.InvalidInputException;

@Service
public class ExpenseService {
    @Autowired
    ExpenseDAL expenseDAL;

    @Autowired
    IncomeDAL incomeDAL;

    @Transactional
    public void saveExpenseForIncome(Integer incomeId, Expense expense){
        Income income = incomeDAL.getIncomeById(incomeId);
        if (income == null) {
            throw new InvalidInputException("Income ID not found: " + incomeId);
        }

        if (income.getExpense() != null) {
            throw new ElementAlreadyExistException("An expense is already associated with this income");
        }

        income.setExpense(expense);
        expense.setIncome(income);

        expenseDAL.saveExpenseForIncome(expense);
    }
}
