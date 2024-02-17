package com.example.expense.controllers;

import com.example.expense.entities.User;
import com.example.expense.exceptions.AlreadyExistsException;
import com.example.expense.exceptions.NotFoundException;
import com.example.expense.services.ExpenseService;
import com.example.expense.services.IncomeService;
import com.example.expense.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    IncomeService incomeService;
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user, BindingResult bindingResult){
        log.info("Request Received for adding user:"+user.getUsername());
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            User createdUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }catch (AlreadyExistsException existsException){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId){
        log.info("Request Received to get user:"+userId);
        try{
            User user = userService.getUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody @Valid User user, BindingResult bindingResult){
        log.info("Request Received to update user:"+userId);
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            User updateUser = userService.updateUser(userId, user);
            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int userId){
        log.info("Request Received to delete user:"+userId);
        try{
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}/savings/{year}")
    public ResponseEntity<Double> getNetSavingsByYear(@PathVariable("id") int userId, @PathVariable("year") int year){
        log.info("Request Received to fetch net savings by year for user:"+userId+" year:"+year);
        try{
            Double savings = incomeService.getNetIncomeByYear(userId, year) - incomeService.getNetIncomeByYear(userId, year);
            return ResponseEntity.status(HttpStatus.OK).body(savings);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}/savings/{month}")
    public ResponseEntity<Double> getNetSavingsByMonth(@PathVariable("id") int userId, @PathVariable("month") int month){
        log.info("Request Received to fetch net savings by month for user:"+userId+" month:"+month);
        try{
            Double savings = incomeService.getNetIncomeByMonth(userId, month) - expenseService.getNetExpensesByMonth(userId, month);
            return ResponseEntity.status(HttpStatus.OK).body(savings);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}/savings")
    public ResponseEntity<Double> getNetSavings(@PathVariable("id") int userId){
        log.info("Request Received to fetch net savings till now for user:"+userId);
        try{
            Double savings = incomeService.getNetIncome(userId) - expenseService.getNetExpenses(userId);
            return ResponseEntity.status(HttpStatus.OK).body(savings);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
