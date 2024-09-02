package com.example.cnExpense.DAL;

import java.util.List;

import com.example.cnExpense.entities.User;

public interface UserDAL {
    User getUserById(Integer id);

    List<User> getAllUsers();

    boolean checkUserExists(User user);

    void saveUser(User user);

    User findUser(User user);

    List<User> UserListByCalendar(String day,String month,String year);

    List<User> UserListByType(String incomeType,String expenseType);
}
