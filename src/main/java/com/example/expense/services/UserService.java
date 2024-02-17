package com.example.expense.services;

import com.example.expense.dal.UserDao;
import com.example.expense.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Transactional
    public User addUser(User user){
        return userDao.addUser(user);
    }
    @Transactional
    public User getUser(int userId){
        return userDao.getUser(userId);
    }
    @Transactional
    public User updateUser(int userId, User user){
        return userDao.updateUser(userId, user);
    }
    @Transactional
    public void deleteUser(int userId){
        userDao.deleteUser(userId);
    }

}
