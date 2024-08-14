package com.microserivce.userservice.services.impl;

import com.microserivce.userservice.entities.User;
import com.microserivce.userservice.exceptions.ResourceNotFoundExceptions;
import com.microserivce.userservice.repositories.UserRespository;
import com.microserivce.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;

    @Autowired
    public UserServiceImpl(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        return this.userRespository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRespository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return this.userRespository.findById(id).orElseThrow(()-> new ResourceNotFoundExceptions("User","id",id));
    }
}
