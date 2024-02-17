package com.example.expense.dal;

import com.example.expense.constants.Constants;
import com.example.expense.entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    EntityManager entityManager;
    @Override
    public User addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(
                (User) session.createQuery(Constants.QueryConstants.FETCH_BY_USERNAME).setParameter("value",user.getUsername()).getSingleResult()
        );
        if(!oldUser.isPresent()){
            throw new RuntimeException();
        }
        Integer incomeId = (Integer)session.save(user);
        return session.get(User.class,incomeId);
    }
    @Override
    public User updateUser(String userId, User user) {
        return null;
    }
    @Override
    public void deleteUser(String userId) {

    }
}
