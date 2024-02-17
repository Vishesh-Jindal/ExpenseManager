package com.example.expense.dal;

import com.example.expense.entities.Income;

public interface IncomeDao {
    public Income addIncome(int userId, Income income);
    public Income updateIncome(int incomeId, Income income);
    public void deleteIncome(int incomeId);
    public double getTotalIncomeInYear(int userId, int year);
    public double getTotalIncomeInMonth(int userId, int month);
    public double getTotalIncome(int userId);
}
