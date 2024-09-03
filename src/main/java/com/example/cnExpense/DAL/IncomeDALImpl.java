package com.example.cnExpense.DAL;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cnExpense.entities.Income;

@Repository
public class IncomeDALImpl implements IncomeDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Income getIncomeById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Income income = session.get(Income.class, id);
        return income;
    }

    @Override
    public void saveIncomeForUser(Income income) {
        // add income to user income list

        Session session = entityManager.unwrap(Session.class);
        session.save(income);
    };
}
