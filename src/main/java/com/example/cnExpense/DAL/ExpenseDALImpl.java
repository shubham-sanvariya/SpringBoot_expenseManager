package com.example.cnExpense.DAL;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cnExpense.entities.Expense;

@Repository
public class ExpenseDALImpl implements ExpenseDAL{

    @Autowired
    EntityManager entityManager;

    @Override
    public void saveExpenseForIncome(Expense expense) {
        Session session = entityManager.unwrap(Session.class);
        session.save(expense);
    }
    
}
