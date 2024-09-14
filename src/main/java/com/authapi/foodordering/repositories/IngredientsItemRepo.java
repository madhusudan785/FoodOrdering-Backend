package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsItemRepo extends JpaRepository<IngredientsItem, Long> {
    List<IngredientsItem> findByRestaurantId(long restaurantId);
}
