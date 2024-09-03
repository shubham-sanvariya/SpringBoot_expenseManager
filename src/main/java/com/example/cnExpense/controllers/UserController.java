package com.example.cnExpense.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cnExpense.entities.User;
import com.example.cnExpense.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/checkUserExist")
    public boolean checkUserExists(@RequestBody User user) {
        return userService.checkUserExists(user);
    }

    @PostMapping("/find")
    public User findUser(@RequestBody User user){
        return userService.findUser(user);
    }

    @GetMapping("/filteredUserListByCalendar")
    public List<User> getUserListByCalendar(
            @RequestParam(value = "day", required = false) String day,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year) {

        return userService.UserListByCalendar(day, month, year);
    }

    @GetMapping("/filteredUserListByType")
    public List<User> getUserListByType(
        @RequestParam(value = "incomeType", required = false) String incomeType,
        @RequestParam(value = "expenseType", required = false) String expenseType) {

            return userService.UserListByType(incomeType,expenseType);
        }
}
