package com.example.cnExpense.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cnExpense.DAL.UserDAL;
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
    public void saveUser(User user){
        User u = userDAL.getUserById(user.getId());
        if (u != null) {
            throw new ElementAlreadyExistException("user already exists");
        }

        userDAL.saveUser(user);
    }

    @Transactional
    public boolean checkUserExists(User user){
        return userDAL.checkUserExists(user);
    }

    @Transactional
    public User findUser(User user){
        User u = userDAL.findUser(user);
        if (u == null) {
            return null;
        }

        return user;
    }

    @Transactional
    public List<User> UserListByCalendar(String day, String month, String year){
        List<User> list = userDAL.UserListByCalendar(day,month,year);
        if (list.isEmpty()) {
            throw new NotFoundException("no user list find by Calender");
        }
        return list;
    }

    @Transactional
    public List<User> UserListByType(String incomeType, String expenseType){
        List<User> list = userDAL.UserListByType(incomeType,expenseType);
        if (list.isEmpty()) {
            throw new NotFoundException("no user list find by income and expense type");
        }
        return list;
    }
}
