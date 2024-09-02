package com.example.cnExpense.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double amount;

    private LocalDate date;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToMany(mappedBy = "incomes")
    private List<User> users;

    @OneToMany(mappedBy = "income")
    private List<IncomeType> incomeTypes;

    public Income() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<IncomeType> getIncomeTypes() {
        return incomeTypes;
    }

    public void setIncomeTypes(List<IncomeType> incomeTypes) {
        this.incomeTypes = incomeTypes;
    }
}
