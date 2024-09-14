package com.authapi.foodordering.services.impl;

import com.authapi.foodordering.models.Category;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.repositories.CategoryRepo;
import com.authapi.foodordering.services.CategoryService;
import com.authapi.foodordering.services.RestaurantService;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category createCategory(String categoryName, Long userId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(categoryName);
        category.setRestaurant(restaurant);


        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(restaurantId);
        return categoryRepo.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long categoryId)throws Exception {
        Optional<Category> category=categoryRepo.findById(categoryId);
       if (category.isEmpty()){
           throw new Exception("Category not found");
       }
       return category.get();
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }
}
