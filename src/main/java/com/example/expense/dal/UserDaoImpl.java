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
                (User) session.createQuery(Constants.QueryConstants.FETCH_BY_USERNAME).setParameter("value",user.getUsername()).uniqueResult()
        );
        if(oldUser.isPresent()){
            throw new RuntimeException();
        }
        Integer incomeId = (Integer)session.save(user);
        return session.get(User.class,incomeId);
    }

    @Override
    public User getUser(int userId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> user = Optional.ofNullable(session.get(User.class,userId));
        if(!user.isPresent()){
            throw new RuntimeException();
        }
        return user.get();
    }

    @Override
    public User updateUser(int userId, User user) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(session.get(User.class,userId));
        if(!oldUser.isPresent()){
            throw new RuntimeException();
        }
        // if user name not same throw error
        oldUser.get().setAddress(user.getAddress());
        oldUser.get().setEmail(user.getEmail());
        oldUser.get().setNickname(user.getNickname());

        session.update(oldUser);

        return session.get(User.class, userId);
    }
    @Override
    public void deleteUser(int userId) {
        Session session = entityManager.unwrap(Session.class);
        Optional<User> oldUser = Optional.ofNullable(session.get(User.class,userId));
        if(!oldUser.isPresent()){
            throw new RuntimeException();
        }
        session.delete(oldUser);
    }
}
