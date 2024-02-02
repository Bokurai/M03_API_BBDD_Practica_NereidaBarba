package com.example.restservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    List<User> findAll();

    static User findOne() {
        return new User();
    }

    User delete();
}
