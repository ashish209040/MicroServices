package com.microserivce.hotelservice.services;

import com.microserivce.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel getHotelById(String id);
    Hotel addHotel(Hotel hotel);
}
