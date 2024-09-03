package com.example.cnExpense.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cnExpense.DAL.UserDAL;
import com.example.cnExpense.entities.ExpenseType;
import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.IncomeType;
import com.example.cnExpense.entities.User;
import com.example.cnExpense.exception.ElementAlreadyExistException;
import com.example.cnExpense.exception.NotFoundException;

@Service
public class UserService {
    
    @Autowired
    UserDAL userDAL;

    @Transactional
    public List<User> getAllUsers(){
        List<User> list = userDAL.getAllUsers();
        if (list.isEmpty()) {
            throw new NotFoundException("user list not found");
        }
        return list;
    }

    @Transactional
    public User getUserById(Integer id){
        User user = userDAL.getUserById(id);
        if (user == null) {
            throw new NotFoundException("user not found with id: " + id);
        }

        return user;
    }

    @Transactional
    public boolean checkUserExists(User user) {
        return userDAL.checkUserExist(user);
    }

    @Transactional
    public void saveUser(User user){
        User u = userDAL.getUserById(user.getId());
        if (u != null) {
            throw new ElementAlreadyExistException("user already exists");
        }

        userDAL.saveUser(user);
    }

    

    @Transactional
    public User findUser(User user){
        return userDAL.findUser(user);
    }

    @Transactional
    public List<User> UserListByCalendar(String day, String month, String year){
        List<User> filteredList = new ArrayList<>();
        List<User> userList= getAllUsers();
        for (User user : userList) {
            List<Income> filteredIncomes = new ArrayList<>();
            for (Income income : user.getIncomes()) {
                if ((income.getDate()!=null) && (day != null && !day.isEmpty() && !(Integer.parseInt(day)==income.getDate().getDayOfMonth()))) {
                    continue;
                }
                else if ((income.getDate()!=null) && (month != null && !month.isEmpty() && !(Integer.parseInt(month)==income.getDate().getMonthValue()))) {
                    continue;
                }
                else if ((income.getDate()!=null) && (year != null && !year.isEmpty() && !(Integer.parseInt(year)==income.getDate().getYear()))) {
                    continue;
                }
                filteredIncomes.add(income);
            }
            user.setIncomes(filteredIncomes);
            filteredList.add(user);
        }
        return filteredList;
    }

    @Transactional
    public List<User> UserListByType(String incomeType, String expenseType){
        List<User> userList = userDAL.getAllUsers();
        List<User> filteredList = new ArrayList<>();
        for (User user : userList) {
            List<Income> filteredIncomes = new ArrayList<>();
            for (Income income : user.getIncomes()) {
                if (incomeType != null && !incomeType.isEmpty()) {
                    boolean foundIncomeType = false;
                    for (IncomeType type : income.getIncomeTypes()) {
                        if (type.getName().equalsIgnoreCase(incomeType)) {
                            foundIncomeType = true;
                            break;
                        }
                    }
                    if (!foundIncomeType) {
                        continue;
                    }
                }
                else if (expenseType != null && !expenseType.isEmpty()) {
                    boolean foundExpenseType = false;
                    for (ExpenseType type : income.getExpense().getExpenseTypes()) {
                        if (type.getName().equalsIgnoreCase(expenseType)) {
                            foundExpenseType = true;
                            break;
                        }
                    }
                    if (!foundExpenseType) {
                        continue;
                    }
                }
                filteredIncomes.add(income);
            }
            user.setIncomes(filteredIncomes);
            filteredList.add(user);
        }
        return filteredList;
    }
}
