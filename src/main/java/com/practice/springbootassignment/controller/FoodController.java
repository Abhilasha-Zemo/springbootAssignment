package com.practice.springbootassignment.controller;

import com.practice.springbootassignment.exception.FoodNotFoundException;
import com.practice.springbootassignment.modal.Food;
import com.practice.springbootassignment.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/food")
    public List<Food> getFood(Model model) {
        return foodService.findAll();
    }

    @GetMapping("/food/{id}")
    public Food getFood(@PathVariable int id) {
        Food food = foodService.findById(id);
        if (food == null) {
            throw new FoodNotFoundException("Food ID not found - " + id);
        }
        return food;
    }

    @PostMapping("/food")
    public Food saveFood(@RequestBody Food food) {
        food.setId(0);
        foodService.save(food);
        return food;
    }

    @PutMapping("/food")
    public Food updateFood(@RequestBody Food food) {
        foodService.save(food);
        return food;
    }

    @DeleteMapping("/food/{id}")
    public String deleteFood(@PathVariable int id) {
        Food food = foodService.findById(id);
        if (food == null) {
            throw new FoodNotFoundException("Food ID not found - " + id);
        }
        foodService.deleteById(id);
        return "Deleted Food ID - " + id;
    }
}
