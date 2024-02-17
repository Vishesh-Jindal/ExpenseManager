package com.example.expense.constants;

public class Constants {
    public class QueryConstants {
        public final static String FETCH_BY_USERNAME = "FROM User WHERE username=:value";
        public final static String FETCH_INCOME_BY_YEAR = "SELECT SUM(amount) FROM Income WHERE YEAR(date)=:year AND user.id=:userId";
        public final static String FETCH_INCOME_BY_MONTH = "SELECT SUM(amount) FROM Income WHERE MONTH(date)=:month AND user.id=:userId";
        public final static String FETCH_INCOME = "SELECT SUM(amount) FROM Income WHERE user.id=:userId";
        public final static String FETCH_EXPENSE_BY_YEAR = "SELECT SUM(amount) FROM Expense WHERE YEAR(date)=:year AND user.id=:userId";
        public final static String FETCH_EXPENSE_BY_MONTH = "SELECT SUM(amount) FROM Expense WHERE MONTH(date)=:month AND user.id=:userId";
        public final static String FETCH_EXPENSE = "SELECT SUM(amount) FROM Expense WHERE user.id=:userId";
    }
}
