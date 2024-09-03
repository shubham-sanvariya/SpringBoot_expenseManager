package com.example.cnExpense.DAL;

import java.util.List;

import com.example.cnExpense.entities.User;

public interface UserDAL {
    List<User> getAllUsers();

    boolean checkUserExist(User user);

    public User getUserById(Integer id);

    public User findUser(User newUser);

    public User saveUser(User user);
}
