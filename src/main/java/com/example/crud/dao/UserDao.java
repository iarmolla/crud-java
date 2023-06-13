package com.example.crud.dao;

import com.example.crud.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUser(int id);
    ResponseEntity<String> deleteUser(int id);
    void createUser(User user);
    void editUser(User user);
    boolean login(User user);
}
