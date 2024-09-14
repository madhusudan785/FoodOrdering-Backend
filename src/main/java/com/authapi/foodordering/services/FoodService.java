package com.authapi.foodordering.services;

import com.authapi.foodordering.models.Category;
import com.authapi.foodordering.models.Food;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    void deleteFood(Long f_id)throws Exception;

    List<Food> getRestaurantFood(Long r_id,boolean isVegetarian,boolean isNonVeg,boolean isSeasonal,String foodCategory)throws Exception;
    List<Food> searchFood(String keyword);

    Food findFoodById(Long f_id)throws Exception;

    Food updateFoodAvailability(Long f_id)throws Exception;

}
