package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> readAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Integer id) {
        return userDAO.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return  userDAO.save(user);
    }

    public void deleteUser(Integer id) {
         userDAO.deleteById(id);
    }

    public User modifyUser(User user, Integer id) {
       return userDAO.save(user);
    }
}
