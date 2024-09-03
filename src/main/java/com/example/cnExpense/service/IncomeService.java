package com.example.cnExpense.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cnExpense.DAL.IncomeDAL;
import com.example.cnExpense.DAL.UserDAL;
import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.User;
import com.example.cnExpense.exception.ElementAlreadyExistException;
import com.example.cnExpense.exception.InvalidInputException;
import com.example.cnExpense.exception.NotFoundException;

@Service
public class IncomeService {
    
    @Autowired
    IncomeDAL incomeDAL;

    @Autowired
    UserDAL userDAL;

    @Transactional
    public Income getIncomeById(Integer id){
        Income income = incomeDAL.getIncomeById(id);
        if(income == null){
            throw new NotFoundException("income not found with Id: " + id);
        }
        return income;
    }

    @Transactional
    public void saveIncomeForUser(Integer userId, Income income){
        User user = userDAL.getUserById(userId);
        if (user == null) {
            throw new InvalidInputException("user id does not exits: " + userId);
        }

        if (user.getIncomes().stream().anyMatch(inc -> inc.getId() == income.getId())) {
            throw new ElementAlreadyExistException("income already exists for user");
        }

        user.getIncomes().add(income);
        userDAL.saveUser(user);
        incomeDAL.saveIncomeForUser(income);
    }
}
