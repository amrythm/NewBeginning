package org.pharmEasy.controller;

import org.pharmEasy.constants.UserMappings;
import org.pharmEasy.dao.UserDao;
import org.pharmEasy.db.model.User;
import org.pharmEasy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(UserMappings.USERS)
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping
    public List<User> findAll() {
        return userDao.findAll();
    }

    @PostMapping(UserMappings.REGISTER)
    public User addNewUser(@RequestBody User user) {
        return userDao.save(user);
    }

    @PutMapping( "{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> user1 = userDao.findById(id);

        if(user1.isEmpty()) {
            throw new ResourceNotFoundException();
        }else {
            user.setId(id);
            return userDao.save(user);
        }
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userDao.deleteById(id);
    }
}
