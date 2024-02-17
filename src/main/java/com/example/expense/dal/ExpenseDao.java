package com.example.expense.dal;

import com.example.expense.entities.Expense;

public interface ExpenseDao {
    public Expense addExpense(String userId, Expense expense);
    public Expense updateExpense(String expenseId, Expense expense);
    public void deleteExpense(String expenseId);
}
