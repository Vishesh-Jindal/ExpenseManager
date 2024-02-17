package com.example.expense.services;

import com.example.expense.dal.UserDao;
import com.example.expense.entities.User;
import com.example.expense.exceptions.AlreadyExistsException;
import com.example.expense.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Transactional
    public User addUser(User user) throws AlreadyExistsException {
            return userDao.addUser(user);
    }
    @Transactional
    public User getUser(int userId) throws NotFoundException {
        return userDao.getUser(userId);
    }
    @Transactional
    public User updateUser(int userId, User user) throws NotFoundException {
        return userDao.updateUser(userId, user);
    }
    @Transactional
    public void deleteUser(int userId) throws NotFoundException {
        userDao.deleteUser(userId);
    }

}
