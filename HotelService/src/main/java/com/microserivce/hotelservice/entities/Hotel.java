package com.microserivce.hotelservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS",length = 100)
    private String address;
    @Column(length = 100)
    private String location;
}

