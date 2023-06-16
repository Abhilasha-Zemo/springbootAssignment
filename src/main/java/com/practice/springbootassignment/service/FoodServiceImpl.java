package com.practice.springbootassignment.service;

import com.practice.springbootassignment.dao.FoodRepository;
import com.practice.springbootassignment.modal.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food findById(int id) {
        Optional<Food> res = foodRepository.findById(id);
        Food food = null;

        if (res.isPresent()) {
            food = res.get();
        } else {
            throw new RuntimeException("Food ID Not Found - " + id);
        }
        return food;
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void deleteById(int id) {
        foodRepository.deleteById(id);
    }
}
