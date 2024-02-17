package com.example.expense.controllers;

import com.example.expense.entities.Income;
import com.example.expense.exceptions.NotFoundException;
import com.example.expense.services.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/income")
@Slf4j
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @PostMapping("/user/{userId}")
    public ResponseEntity<Income> addIncome(@PathVariable("userId") int userId, @RequestBody @Valid Income income, BindingResult bindingResult){
        log.info("Request Received to add income for user:"+userId);
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Income createdIncome = incomeService.addIncome(userId, income);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> updateIncome(@PathVariable("incomeId") int incomeId, @RequestBody @Valid Income income, BindingResult bindingResult){
        log.info("Request Received to update income:"+incomeId);
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Income updatedIncome = incomeService.updateIncome(incomeId, income);
            return ResponseEntity.status(HttpStatus.OK).body(updatedIncome);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable("incomeId") int incomeId){
        log.info("Request Received to delete income:"+incomeId);
        try{
            incomeService.deleteIncome(incomeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user/{userId}/{year}")
    public ResponseEntity<Double> getNetIncomeByYear(@PathVariable("id") int userId, @PathVariable("year") int year){
        log.info("Request Received to fetch net income by year for user:"+userId+" year:"+year);
        try{
            Double income = incomeService.getNetIncomeByYear(userId, year);
            return ResponseEntity.status(HttpStatus.OK).body(income);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user/{userId}/{month}")
    public ResponseEntity<Double> getNetIncomeByMonth(@PathVariable("id") int userId, @PathVariable("month") int month){
        log.info("Request Received to fetch net income by month for user:"+userId+" month:"+month);
        try{
            Double income = incomeService.getNetIncomeByMonth(userId, month);
            return ResponseEntity.status(HttpStatus.OK).body(income);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Double> getNetIncome(@PathVariable("id") int userId){
        log.info("Request Received to fetch net income till now for user:"+userId);
        try{
            Double income = incomeService.getNetIncome(userId);
            return ResponseEntity.status(HttpStatus.OK).body(income);
        }catch (NotFoundException notFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
