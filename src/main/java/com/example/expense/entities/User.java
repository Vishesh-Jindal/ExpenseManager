package com.example.expense.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "nickname")
    String nickname;
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;
    @OneToMany(mappedBy = "user")
    Set<Expense> expenseSet = new HashSet<>(); // Needed if we want to delete user
    @OneToMany(mappedBy = "user")
    Set<Income> incomeSet = new HashSet<>(); // Needed if we want to delete user
}
