package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.User;

public interface IncomeDAL {
    
    Income getIncomeById(Integer id);

    Income saveIncome(User user,Income income);
}
