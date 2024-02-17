package com.example.expense.services;

import com.example.expense.dal.ExpenseDao;
import com.example.expense.entities.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseDao expenseDao;
    @Transactional
    public Expense addExpense(int userId, Expense expense){
        return expenseDao.addExpense(userId, expense);
    }
    @Transactional
    public Expense updateExpense(int expenseId, Expense expense){
        return expenseDao.updateExpense(expenseId, expense);
    }
    @Transactional
    public void deleteExpense(int expenseId){
        expenseDao.deleteExpense(expenseId);
    }
}
