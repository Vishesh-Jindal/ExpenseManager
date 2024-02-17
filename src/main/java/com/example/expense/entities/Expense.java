package com.example.expense.entities;

import com.example.expense.enums.ExpenseType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "amount")
    double amount;
    @Column(name = "date")
    LocalDate date;
    @Column(name = "description")
    String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "expenseType")
    ExpenseType expenseType;
}
