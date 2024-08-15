package com.microserivce.userservice.services.impl;

import com.microserivce.userservice.entities.Hotel;
import com.microserivce.userservice.entities.Rating;
import com.microserivce.userservice.entities.User;
import com.microserivce.userservice.exceptions.ResourceNotFoundExceptions;
import com.microserivce.userservice.repositories.UserRespository;
import com.microserivce.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final UserRespository userRespository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRespository userRespository, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        List<User> users = this.userRespository.findAll();
        List<User> userList = users.stream().map(user -> {
            Rating[] object = this.restTemplate.getForObject("http://localhost:8082/ratings/users/" + user.getUserId(), Rating[].class);
            List<Rating> ratingList = Arrays.stream(object).toList();
            ratingList.stream().map(rating -> {
                Hotel hotel = this.restTemplate.getForObject("http://localhost:8081/hotels/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
            return user;
        }).collect(Collectors.toList());
        return userList;
    }

    @Override
    public User getUserById(String id) {
        User user = this.userRespository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("User", "id", id));
        Rating[] ratings = restTemplate.getForObject("http://localhost:8082/ratings/users/"+user.getUserId(), Rating[].class);
        List<Rating> ratings1 = Arrays.stream(ratings).toList();
        List<Rating> ratingList = ratings1.stream().map(rating -> {
            Hotel forObject = restTemplate.getForObject("http://localhost:8081/hotels/" + rating.getHotelId(), Hotel.class);
            rating.setHotel(forObject);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
