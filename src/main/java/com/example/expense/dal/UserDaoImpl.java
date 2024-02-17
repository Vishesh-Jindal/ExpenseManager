package com.example.expense.dal;

import com.example.expense.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public User addUser(User user) {
        return null;
    }
    @Override
    public User updateUser(String userId, User user) {
        return null;
    }
    @Override
    public void deleteUser(String userId) {

    }
}
