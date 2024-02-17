package com.example.expense.controllers;

import com.example.expense.entities.Expense;
import com.example.expense.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/{userId}")
    public ResponseEntity<Expense> addExpense(@PathVariable("userId") int userId, @RequestBody Expense expense){
        Expense createdExpense = expenseService.addExpense(userId, expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }
    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") int expenseId, @RequestBody Expense expense){
        Expense updateExpense = expenseService.updateExpense(expenseId, expense);
        return ResponseEntity.status(HttpStatus.OK).body(updateExpense);
    }
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("expenseId") int expenseId){
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
