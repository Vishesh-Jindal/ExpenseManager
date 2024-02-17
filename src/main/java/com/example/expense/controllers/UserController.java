package com.example.expense.controllers;

import com.example.expense.entities.User;
import com.example.expense.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        log.info("Request Received for adding user:"+user.getUsername());
        User createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId){
        log.info("Request Received to get user:"+userId);
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user){
        log.info("Request Received to update user:"+userId);
        User updateUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int userId){
        log.info("Request Received to delete user:"+userId);
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
