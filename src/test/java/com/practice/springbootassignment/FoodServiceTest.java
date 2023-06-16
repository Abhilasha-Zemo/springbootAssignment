package com.practice.springbootassignment;

import com.practice.springbootassignment.dao.FoodRepository;
import com.practice.springbootassignment.modal.Food;
import com.practice.springbootassignment.service.FoodService;
import com.practice.springbootassignment.service.FoodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService = new FoodServiceImpl();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindFoodById() throws Exception {
        Food food = new Food(1, "new", "indian", "Biryani");

        when(foodRepository.findById(1)).thenReturn(Optional.of(food));

        Food res = foodService.findById(1);
        assertEquals(food, res);
        verify(foodRepository, times(1)).findById(1);
    }

    @Test
    void testFindAllFood() {
        List<Food> food = new ArrayList<>();
        food.add(new Food());
        food.add(new Food());

        when(foodRepository.findAll()).thenReturn(food);

        List<Food> res = foodService.findAll();

        assertEquals(food, res);
        verify(foodRepository, times(1)).findAll();
    }

    @Test
    void testSaveFood() {
        Food food = new Food(1, "new", "indian", "Biryani");

        when(foodRepository.save(food)).thenReturn(food);
        Food res = foodService.save(food);

        assertEquals(food, res);
        verify(foodRepository, atLeastOnce()).save(food);
    }

    @Test
    void testDeleteFood() {
        Food food = new Food(1, "new", "indian", "Biryani");

        when(foodRepository.findById(1)).thenReturn(Optional.of(food));

        foodService.deleteById(1);
        verify(foodRepository, times(1)).deleteById(1);
    }
}
