package com.example.expense.dal;

import com.example.expense.constants.Constants;
import com.example.expense.entities.Income;
import com.example.expense.entities.User;
import com.example.expense.exceptions.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class IncomeDaoImpl implements IncomeDao {
    @Autowired
    EntityManager entityManager;
    @Override
    public Income addIncome(int userId, Income income) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new NotFoundException();
        }
        income.setUser(user.get());
        Integer incomeId = (Integer)session.save(income);
        return session.get(Income.class,incomeId);
    }
    @Override
    public Income updateIncome(int incomeId, Income income) {
        Session session = entityManager.unwrap(Session.class);
        Optional<Income> oldIncome = Optional.ofNullable(session.get(Income.class,incomeId));
        if(!oldIncome.isPresent()){
            throw new NotFoundException();
        }
        oldIncome.get().setDate(income.getDate());
        oldIncome.get().setIncomeType(income.getIncomeType());
        oldIncome.get().setAmount(income.getAmount());
        oldIncome.get().setDescription(income.getDescription());
        session.update(oldIncome.get());
        return session.get(Income.class, incomeId);
    }
    @Override
    public void deleteIncome(int incomeId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<Income> income = Optional.ofNullable(session.get(Income.class,incomeId));
        if(!income.isPresent()){
            throw new NotFoundException();
        }
        session.delete(income.get());
    }

    @Override
    public double getTotalIncomeInYear(int userId, int year) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new NotFoundException();
        }
        Double result = Optional.ofNullable(session.createQuery(Constants.QueryConstants.FETCH_INCOME_BY_YEAR,Double.class).setParameter("year",year).setParameter("userId",userId).getSingleResult()).orElse(0.0);
        return result;
    }

    @Override
    public double getTotalIncomeInMonth(int userId, int month) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new NotFoundException();
        }
        Double result = Optional.ofNullable(session.createQuery(Constants.QueryConstants.FETCH_INCOME_BY_MONTH,Double.class).setParameter("month",month).setParameter("userId",userId).getSingleResult()).orElse(0.0);
        return result;
    }

    @Override
    public double getTotalIncome(int userId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new NotFoundException();
        }
        Double result = Optional.ofNullable(session.createQuery(Constants.QueryConstants.FETCH_INCOME,Double.class).setParameter("userId",userId).getSingleResult()).orElse(0.0);
        return result;
    }
}
