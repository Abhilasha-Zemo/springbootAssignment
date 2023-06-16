package com.practice.springbootassignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springbootassignment.controller.FoodController;
import com.practice.springbootassignment.modal.Food;
import com.practice.springbootassignment.service.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = FoodController.class)
class FoodControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FoodService foodService;

    @Test
    void testFindAllFood() throws Exception {
        List<Food> food = new ArrayList<>();
        food.add(new Food());
        food.add(new Food());

        when(foodService.findAll()).thenReturn(food);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/food");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(food);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(outputJson, expectedJson);
    }

    @Test
    void testUpdateMovie() throws Exception {
        Food food = new Food(1, "new", "indian", "Biryani");

        String inputJson = this.mapToJson(food);
        String URI = "/api/food";

        when(foodService.save(any(Food.class))).thenReturn(food);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        String outputJson = response.getContentAsString();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testFindMovieById() throws Exception {
        Food food = new Food(1, "new", "indian", "Biryani");
        when(foodService.findById(1)).thenReturn(food);

        String uri = "/api/food/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(food);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson, is(equalTo(expectedJson)));
    }

//    @Test
//    void testFindMovieById_ThrowsException() throws Exception {
////        when(movieService.findById(0)).thenThrow(new MovieNotFoundException("Movie ID Not Found - 0"));
////        Throwable exception = assertThrows(MovieNotFoundException.class, () -> movieService.findById(0));
////        assertEquals("Movie ID Not Found - 0", exception.getMessage());
//
//        when(movieService.findById(0)).thenReturn(Optional.ofNullable(null));
//        String exceptionParam = "not_found";
//
//        mockMvc.perform(get("/api/movies/0", exceptionParam)
//                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isNotFound())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MovieNotFoundException))
//                .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
//
//    }

    @Test
    void testPostMovie() throws Exception {
        Food food = new Food(1, "new", "indian", "Biryani");

        String inputJson = this.mapToJson(food);
        String URI = "/api/food";

        when(foodService.save(any(Food.class))).thenReturn(food);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        String outputJson = response.getContentAsString();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    void testDeleteFood() throws Exception {
        int id = 1;
        Food food = new Food(1, "new", "indian", "Biryani");
        food.setId(1);
        when(foodService.findById(id)).thenReturn(food);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/food/{id}", id)).andExpect(status().isOk());
        verify(foodService, times(1)).findById(id);
    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
