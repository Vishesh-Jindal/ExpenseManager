package com.example.expense.services;

import com.example.expense.dal.ExpenseDao;
import com.example.expense.entities.Expense;
import com.example.expense.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseDao expenseDao;
    @Transactional
    public Expense addExpense(int userId, Expense expense) throws NotFoundException {
        return expenseDao.addExpense(userId, expense);
    }
    @Transactional
    public Expense updateExpense(int expenseId, Expense expense) throws NotFoundException {
        return expenseDao.updateExpense(expenseId, expense);
    }
    @Transactional
    public void deleteExpense(int expenseId) throws NotFoundException {
        expenseDao.deleteExpense(expenseId);
    }
    @Transactional
    public double getNetExpensesByYear(int userId, int year){
        return expenseDao.getTotalExpenseInYear(userId, year);
    }
    @Transactional
    public double getNetExpensesByMonth(int userId, int month){
        return expenseDao.getTotalExpenseInMonth(userId, month);
    }
    @Transactional
    public double getNetExpenses(int userId){
        return expenseDao.getTotalExpense(userId);
    }
}
