package com.practice.springbootassignment.dao;

import com.practice.springbootassignment.modal.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
