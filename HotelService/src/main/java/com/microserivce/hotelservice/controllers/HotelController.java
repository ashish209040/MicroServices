package com.microserivce.hotelservice.controllers;

import com.microserivce.hotelservice.entities.Hotel;
import com.microserivce.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(this.hotelService.addHotel(hotel), HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<>(this.hotelService.getAllHotels(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        return new ResponseEntity<>(this.hotelService.getHotelById(id), HttpStatus.OK);
    }
}
