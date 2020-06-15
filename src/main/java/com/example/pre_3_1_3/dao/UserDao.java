package com.example.pre_3_1_3.dao;

import com.example.pre_3_1_3.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    User findById(Long id);
    User findByName(String name);
    List<User> findAll();
    void deleteById(Long id);
    void delete(User user);
}
