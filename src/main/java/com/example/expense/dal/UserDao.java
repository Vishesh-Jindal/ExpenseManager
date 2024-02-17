package com.example.expense.dal;

import com.example.expense.entities.User;

public interface UserDao {
    public User addUser(User user);
    public User getUser(int userId);
    public User updateUser(int userId, User user);
    public void deleteUser(int userId);
}
