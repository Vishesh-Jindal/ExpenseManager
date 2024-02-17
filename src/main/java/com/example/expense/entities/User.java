package com.example.expense.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "username")
    String username;
    @Column(name = "nickname")
    String nickname;
    @NotNull
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;
    @OneToMany(mappedBy = "user")
    Set<Expense> expenseSet = new HashSet<>(); // Needed if we want to delete user

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Expense> getExpenseSet() {
        return expenseSet;
    }

    public void setExpenseSet(Set<Expense> expenseSet) {
        this.expenseSet = expenseSet;
    }

    public Set<Income> getIncomeSet() {
        return incomeSet;
    }

    public void setIncomeSet(Set<Income> incomeSet) {
        this.incomeSet = incomeSet;
    }

    @OneToMany(mappedBy = "user")
    Set<Income> incomeSet = new HashSet<>(); // Needed if we want to delete user
}
