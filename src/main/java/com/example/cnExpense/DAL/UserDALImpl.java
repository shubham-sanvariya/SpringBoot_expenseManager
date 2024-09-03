package com.example.cnExpense.DAL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cnExpense.entities.User;

@Repository
public class UserDALImpl implements UserDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users = session.createQuery("SELECT u FROM User u", User.class).getResultList();
        return users;
    }

    @Override
    public boolean checkUserExists(User user) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<Long> query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username OR u.nickname = :nickname OR u.email = :email", Long.class);
        query.setParameter("username", user.getUsername());
        query.setParameter("nickname", user.getNickname());
        query.setParameter("email", user.getEmail());
        Long count = query.getSingleResult();
        return count > 0;
        // User u = session.get(User.class, user.getId());
        // return u == null ? false : true;
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }

    @Override
    public User findUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        User u = session.find(User.class,user);
        return u;
    }

    @Override
    public List<User> UserListByCalendar(String day, String month, String year) {
        List<User> list = getAllUsers();
        List<User> res = new ArrayList<>();

        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        if (!list.isEmpty()) {
            res = list.stream()
            .filter(u -> u.getIncomes().stream()
                .anyMatch(income -> income.getDate().equals(date)))
           .collect(Collectors.toList());
        }
        return res;
    }

    @Override
    public List<User> UserListByType(String incomeType, String expenseType) {
        // String hql = "SELECT DISTINCT u FROM User u JOIN u.expenses e JOIN e.expenseTypes et " +
        //         "JOIN u.incomes i JOIN i.incomeTypes it " +
        //         "WHERE et = :expenseType AND it = :incomeType";

        // TypedQuery<User> query = session.createQuery(hql, User.class);
        // query.setParameter("expenseType", expenseType);
        // query.setParameter("incomeType", incomeType);

        // return query.getResultList();
        List<User> list = getAllUsers();
        List<User> res = new ArrayList<>();

        if (!list.isEmpty()) {
            res = list.stream().filter(u -> u.getIncomes().stream()
            .anyMatch(income -> income.getIncomeTypes().stream()
            .anyMatch(type -> type.getName().equals(incomeType))) 
            &&
             u.getExpenses().stream()
             .anyMatch(expense -> expense.getExpenseTypes().stream()
             .anyMatch(type -> type.getName().equals(expenseType))))
            .collect(Collectors.toList());
        }

        return res;
    }

}
