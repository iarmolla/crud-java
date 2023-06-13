package com.example.crud.controllers;

import com.example.crud.dao.UserDao;
import com.example.crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.getUsers();
    }
    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return userDao.getUser(id);
    }
    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return userDao.deleteUser(id);
    }
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userDao.createUser(user);
    }
    @RequestMapping(value = "users", method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) {
        userDao.editUser(user);
    }
}
