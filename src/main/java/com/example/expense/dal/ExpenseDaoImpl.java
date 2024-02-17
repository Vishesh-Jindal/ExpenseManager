package com.example.expense.dal;

import com.example.expense.entities.Expense;
import com.example.expense.entities.User;
import com.example.expense.exceptions.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class ExpenseDaoImpl implements ExpenseDao{
    @Autowired
    EntityManager entityManager;
    @Override
    public Expense addExpense(int userId, Expense expense) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new NotFoundException();
        }
        expense.setUser(user.get());
        Integer expenseId = (Integer)session.save(expense);
        return session.get(Expense.class,expenseId);
    }

    @Override
    public Expense updateExpense(int expenseId, Expense expense) {
        Session session = entityManager.unwrap(Session.class);
        Optional<Expense> oldExpense = Optional.ofNullable(session.get(Expense.class,expenseId));
        if(!oldExpense.isPresent()){
            throw new NotFoundException();
        }
        oldExpense.get().setDate(expense.getDate());
        oldExpense.get().setExpenseType(expense.getExpenseType());
        oldExpense.get().setAmount(expense.getAmount());
        oldExpense.get().setDescription(expense.getDescription());
        session.update(oldExpense.get());
        return session.get(Expense.class, expenseId);
    }

    @Override
    public void deleteExpense(int expenseId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<Expense> expense = Optional.ofNullable(session.get(Expense.class,expenseId));
        if(!expense.isPresent()){
            throw new NotFoundException();
        }
        session.delete(expense.get());
    }
}
