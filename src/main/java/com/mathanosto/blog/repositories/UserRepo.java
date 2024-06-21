package com.mathanosto.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mathanosto.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
