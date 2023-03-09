package com.rakesh.BloggingApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.BloggingApplication.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
