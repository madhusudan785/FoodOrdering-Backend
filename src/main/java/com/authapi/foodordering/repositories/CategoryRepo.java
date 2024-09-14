package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByRestaurantId(Long restaurantId);

}
