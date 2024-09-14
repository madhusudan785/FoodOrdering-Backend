package com.authapi.foodordering.services;

import com.authapi.foodordering.models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String categoryName,Long  userId) throws Exception;

    List<Category> getAllCategories();

    List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception;

    Category findCategoryById(Long categoryId) throws Exception;

    Category updateCategory(Category category);
}
