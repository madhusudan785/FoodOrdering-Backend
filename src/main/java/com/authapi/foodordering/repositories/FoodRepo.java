package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepo extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<Food>searchFood(@Param("keyword") String keyword);
    List<Food> findByRestaurantId(Long restaurantId);
}
