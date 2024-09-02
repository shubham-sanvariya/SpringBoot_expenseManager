package com.example.cnExpense.entities;

import javax.persistence.Entity;

@Entity
public class IncomeType {
    private int id;

    private String name;

    private Income income;

    public IncomeType() {
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

}
