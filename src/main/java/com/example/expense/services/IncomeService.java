package com.example.expense.services;

import com.example.expense.dal.IncomeDao;
import com.example.expense.entities.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IncomeService {
    @Autowired
    IncomeDao incomeDao;
    @Transactional
    public Income addIncome(int userId, Income income){
        return incomeDao.addIncome(userId, income);
    }
    @Transactional
    public Income updateIncome(int incomeId, Income income){
        return incomeDao.updateIncome(incomeId, income);
    }
    @Transactional
    public void deleteIncome(int incomeId){
        incomeDao.deleteIncome(incomeId);
    }
}
