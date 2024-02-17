package com.example.expense.dal;

import com.example.expense.entities.User;

public interface UserDao {
    public User addUser(User user);
    public User updateUser(String userId, User user);
    public void deleteUser(String userId);
}
