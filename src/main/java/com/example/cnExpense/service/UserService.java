package com.example.cnExpense.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cnExpense.DAL.UserDAL;
import com.example.cnExpense.entities.User;
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

    
}
