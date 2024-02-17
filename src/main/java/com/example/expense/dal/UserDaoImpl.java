package com.example.expense.dal;

import com.example.expense.constants.Constants;
import com.example.expense.entities.Expense;
import com.example.expense.entities.Income;
import com.example.expense.entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    EntityManager entityManager;
    @Override
    public User addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(
                (User) session.createQuery(Constants.QueryConstants.FETCH_BY_USERNAME, User.class).setParameter("value",user.getUsername()).uniqueResult()
        );
        if(oldUser.isPresent()){
            throw new RuntimeException();
        }
        Integer userId = (Integer)session.save(user);
        User createdUser = this.getUser(userId);
        return createdUser;
    }

    @Override
    public User getUser(int userId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new RuntimeException();
        }
        return user.get();
    }

    @Override
    public User updateUser(int userId, User user) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(session.get(User.class,userId));
        if(!oldUser.isPresent()){
            throw new RuntimeException();
        }
        // if user name not same throw error
        oldUser.get().setAddress(user.getAddress());
        oldUser.get().setEmail(user.getEmail());
        oldUser.get().setNickname(user.getNickname());

        session.update(oldUser.get());

        return this.getUser(userId);
    }
    @Override
    public void deleteUser(int userId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(session.get(User.class,userId));
        if(!oldUser.isPresent()){
            throw new RuntimeException();
        }
        Set<Income> incomeSet = oldUser.get().getIncomeSet();
        Set<Expense> expenseSet = oldUser.get().getExpenseSet();
        for(Income income:incomeSet){
            session.delete(income);
        }
        for(Expense expense:expenseSet){
            session.delete(expense);
        }
        session.delete(oldUser.get());
    }
}
