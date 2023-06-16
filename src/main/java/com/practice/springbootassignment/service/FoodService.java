package com.practice.springbootassignment.service;

import com.practice.springbootassignment.modal.Food;

import java.util.List;

public interface FoodService {
    List<Food> findAll();

    Food findById(int id);

    Food save(Food food);

    void deleteById(int id);
}
