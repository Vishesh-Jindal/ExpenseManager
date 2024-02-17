package com.example.expense.entities;

import com.example.expense.enums.IncomeType;

import javax.persistence.*;
import java.time.LocalDate;

public class Income {
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
    @Column(name = "incomeType")
    IncomeType incomeType;
}
