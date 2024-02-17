package com.example.expense.controllers;

import com.example.expense.entities.Expense;
import com.example.expense.exceptions.NotFoundException;
import com.example.expense.services.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/expense")
@Slf4j
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Expense> addExpense(@PathVariable("userId") int userId, @RequestBody @Valid Expense expense, BindingResult bindingResult){
        log.info("Request Received to add expense for user:"+userId);
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Expense createdExpense = expenseService.addExpense(userId, expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable("expenseId") int expenseId, @RequestBody @Valid Expense expense, BindingResult bindingResult){
        log.info("Request Received to update expense:"+expenseId);
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Expense updateExpense = expenseService.updateExpense(expenseId, expense);
            return ResponseEntity.status(HttpStatus.OK).body(updateExpense);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("expenseId") int expenseId){
        log.info("Request Received to delete expense:"+expenseId);
        try{
            expenseService.deleteExpense(expenseId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
