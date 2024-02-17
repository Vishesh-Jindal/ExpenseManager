package com.example.expense.dal;

import com.example.expense.entities.Income;

public interface IncomeDao {
    public Income addIncome(String userId, Income income);
    public Income updateIncome(String incomeId, Income income);
    public void deleteIncome(String incomeId);
}
