package com.microserivce.ratingservice.controllers;

import com.microserivce.ratingservice.entities.Rating;
import com.microserivce.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(this.ratingService.addRating(rating), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return new ResponseEntity<>(this.ratingService.getRatings(), HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(this.ratingService.getAllRatingsByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return new ResponseEntity<>(this.ratingService.getAllRatingsByHotelId(hotelId), HttpStatus.OK);
    }
}
