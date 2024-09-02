package com.example.cnExpense.entities;

import javax.persistence.Entity;

@Entity
public class ExpenseType {
    private int id;

    private String name;

    private Expense expense;

    public ExpenseType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    
}
