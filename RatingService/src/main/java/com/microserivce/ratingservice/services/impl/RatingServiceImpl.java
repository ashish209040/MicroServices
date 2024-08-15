package com.microserivce.ratingservice.services.impl;

import com.microserivce.ratingservice.entities.Rating;
import com.microserivce.ratingservice.exceptions.ResourceNotFoundException;
import com.microserivce.ratingservice.repositories.RatingRepository;
import com.microserivce.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }
    @Override
    public Rating addRating(Rating rating) {
        rating.setId(UUID.randomUUID().toString());
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        return this.ratingRepository.findAllByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(String id) {
        return this.ratingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rating","id",id));
    }
}
