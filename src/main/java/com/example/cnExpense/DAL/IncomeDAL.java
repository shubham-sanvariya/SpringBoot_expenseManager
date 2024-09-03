package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Income;

public interface IncomeDAL {
    
    Income getIncomeById(Integer id);

    void saveIncomeForUser(Income income);
}
