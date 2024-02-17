package com.example.expense.dal;

import com.example.expense.entities.Expense;

public interface ExpenseDao {
    public Expense addExpense(int userId, Expense expense);
    public Expense updateExpense(int expenseId, Expense expense);
    public void deleteExpense(int expenseId);
}
