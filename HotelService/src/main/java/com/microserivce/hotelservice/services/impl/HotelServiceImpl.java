package com.microserivce.hotelservice.services.impl;

import com.microserivce.hotelservice.entities.Hotel;
import com.microserivce.hotelservice.exceptions.ResourceNotFoundException;
import com.microserivce.hotelservice.repositories.HotelRepository;
import com.microserivce.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }
    @Override

    public Hotel getHotelById(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel", "id", id));
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return this.hotelRepository.save(hotel);
    }
}
