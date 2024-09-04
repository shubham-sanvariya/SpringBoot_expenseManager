package com.example.cnExpense.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.User;
import com.example.cnExpense.service.IncomeService;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    
    @Autowired
    IncomeService incomeService;

    @GetMapping("/{incomeid}")
    public Income getIncomeById(@PathVariable Integer incomeid){
        return incomeService.getIncomeById(incomeid);
    }

    @PostMapping("/save/{userId}")
    public void saveIncomeForUser(@PathVariable Integer userId, @RequestBody Income income){
        User user = new User();
        user.setId(userId);
        incomeService.saveIncomeForUser(user,income);
    }
}
