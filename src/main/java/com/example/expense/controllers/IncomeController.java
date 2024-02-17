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

@RestController
@RequestMapping("/income")
@Slf4j
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @PostMapping("/user/{userId}")
    public ResponseEntity<Income> addIncome(@PathVariable("userId") int userId, @RequestBody Income income, BindingResult bindingResult){
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
    public ResponseEntity<Income> updateIncome(@PathVariable("incomeId") int incomeId, @RequestBody Income income, BindingResult bindingResult){
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
}
