package com.microserivce.ratingservice.services;

import com.microserivce.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating addRating(Rating rating);
    List<Rating> getAllRatingsByUserId(String userId);
    List<Rating> getAllRatingsByHotelId(String ratingId);
    List<Rating> getRatings();
    Rating getRatingById(String id );

}
