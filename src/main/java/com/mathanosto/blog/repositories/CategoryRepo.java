package com.mathanosto.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mathanosto.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
