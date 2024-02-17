package com.example.expense.services;

import com.example.expense.dal.IncomeDao;
import com.example.expense.entities.Income;
import com.example.expense.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IncomeService {
    @Autowired
    IncomeDao incomeDao;
    @Transactional
    public Income addIncome(int userId, Income income) throws NotFoundException {
        return incomeDao.addIncome(userId, income);
    }
    @Transactional
    public Income updateIncome(int incomeId, Income income) throws NotFoundException {
        return incomeDao.updateIncome(incomeId, income);
    }
    @Transactional
    public void deleteIncome(int incomeId) throws NotFoundException {
        incomeDao.deleteIncome(incomeId);
    }
    @Transactional
    public double getNetIncomeByYear(int userId, int year){
        return incomeDao.getTotalIncomeInYear(userId, year);
    }
    @Transactional
    public double getNetIncomeByMonth(int userId, int month){
        return incomeDao.getTotalIncomeInYear(userId, month);
    }
    @Transactional
    public double getNetIncome(int userId){
        return incomeDao.getTotalIncome(userId);
    }
}
