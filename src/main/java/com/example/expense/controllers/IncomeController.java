package com.example.expense.controllers;

import com.example.expense.entities.Income;
import com.example.expense.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;
    @PostMapping("/{userId}")
    public ResponseEntity<Income> addIncome(@PathVariable("userId") int userId, @RequestBody Income income){
        Income createdIncome = incomeService.addIncome(userId, income);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
    }
    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> updateIncome(@PathVariable("incomeId") int incomeId, @RequestBody Income income){
        Income updatedIncome = incomeService.updateIncome(incomeId, income);
        return ResponseEntity.status(HttpStatus.OK).body(updatedIncome);
    }
    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable("incomeId") int incomeId){
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
