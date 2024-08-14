package com.microserivce.userservice.services;

import com.microserivce.userservice.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(String id);
}
