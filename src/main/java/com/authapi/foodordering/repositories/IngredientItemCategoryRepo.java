package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.IngredientItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemCategoryRepo extends JpaRepository<IngredientItemCategory, Long> {
    List<IngredientItemCategory> findByRestaurantId(long restaurantId);
}
