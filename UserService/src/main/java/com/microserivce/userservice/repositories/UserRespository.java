package com.microserivce.userservice.repositories;

import com.microserivce.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, String> {
}
