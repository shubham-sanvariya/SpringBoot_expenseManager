package com.example.cnExpense.DAL;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cnExpense.entities.User;

@Repository
public class UserDALImpl implements UserDAL{
    
    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class,id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users = session.createQuery("SELCT u FROM User u",User.class).getResultList();
        return users;
    }

    @Override
    public boolean checkUserExists(User user) {
        Session session = entityManager.unwrap(Session.class);
        User u = session.get(User.class, user.getId());
        // if (u == null) {
        //     return false;
        // }
        return u == null ? false : true;
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }


}
