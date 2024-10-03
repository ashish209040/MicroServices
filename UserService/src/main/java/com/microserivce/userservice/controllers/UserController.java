package com.microserivce.userservice.controllers;

import com.microserivce.userservice.entities.User;
import com.microserivce.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelServiceBreaker", fallbackMethod = "ratingHotelFallBackHandler")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<User> ratingHotelFallBackHandler(@PathVariable String id, Exception e){
        logger.info("Service is down so Fallback is executed {}.",e.getMessage() );
        User user=User.builder().userId(UUID.randomUUID().toString()).email("dummy@gmail.com").about("This is a Dummy User").name("Dummy User").build();
        return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);
    }
    @GetMapping("/")
//    @CircuitBreaker(name = "ratingHotelServiceBreaker", fallbackMethod = "allUserRatingHotelFallBackHandler")
//    @Retry(name = "ratingHotelService", fallbackMethod = "allUserRatingHotelFallBackHandler" )
    @RateLimiter(name = "userRatingService", fallbackMethod = "allUserRatingHotelFallBackHandler")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    public ResponseEntity<List<User>> allUserRatingHotelFallBackHandler(Exception e){
        logger.info("Service is down so Fallback is executed {}.",e.getMessage() );
        List<User> users = new ArrayList<>();
        users.add(User.builder().userId(UUID.randomUUID().toString()).email("dummy@gmail.com").about("This is a Dummy User").name("Dummy User").build());
        return new ResponseEntity<>(users, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
  