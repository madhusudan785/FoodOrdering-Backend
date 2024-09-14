package com.authapi.foodordering.services.impl;

import com.authapi.foodordering.models.Category;
import com.authapi.foodordering.models.Food;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.repositories.CategoryRepo;
import com.authapi.foodordering.repositories.FoodRepo;
import com.authapi.foodordering.repositories.RestaurantRepo;
import com.authapi.foodordering.request.CreateFoodRequest;
import com.authapi.foodordering.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {
        Food food= new Food();
        if (category.getId() == null) {
            category = categoryRepo.save(category); // Persist the category if it is not saved
        } else {
            category = categoryRepo.findById(category.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }


        food.setName(category.getName());

        food.setRestaurant(restaurant);

        food.setFoodCategory(category);

        food.setDescription(request.getFoodDescription());

        food.setPrice(request.getFoodPrice());

        food.setImages(request.getFoodImage());

        food.setIngredientsItems(request.getIngredients());

        food.setSeasonal(request.isSeasonal());

        food.setVegetarian(request.isVegetarian());

        Food save = foodRepo.save(food);

        restaurant.getFoods().add(save);

        return save;

    }

    @Override
    public void deleteFood(Long f_id) throws Exception {
        Food food = findFoodById(f_id);
        food.setRestaurant(null);
        foodRepo.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long r_id, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) throws Exception {
        List<Food> food = foodRepo.findByRestaurantId(r_id);
         if (isVegetarian) {
             food= filtererVegetarian(food,isVegetarian);
         }
         if (isNonVeg) {
             food=filterByNonVeg(food,isNonVeg);
         }
         if (isSeasonal) {
             food=filterBySeasonal(food,isSeasonal);
         }
         if (foodCategory != null && !foodCategory.isEmpty()) {
             food=filterByCategory(food,foodCategory);

         }
        return food;
    }

    private List<Food> filterByCategory(List<Food> food, String foodCategory) {
      return food.stream().filter(food1 ->
      {
          if (food1 != null) {
              return food1.getFoodCategory().getName().equals(foodCategory);
          }
          return false;
      }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> food, boolean isSeasonal) {
        return food.stream().filter(food1 -> food1.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> food, boolean isNonVeg) {
        return food.stream().filter(food1 -> !food1.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filtererVegetarian(List<Food> food, boolean isVegetarian) {
        return food.stream().filter(food1 -> food1.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepo.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long f_id) throws Exception {
        Optional<Food> optionalFood = foodRepo.findById(f_id);
        if (optionalFood.isEmpty()) {
            throw new Exception("food not present ");
        }

        return optionalFood.get();
    }

    @Override
    public Food updateFoodAvailability(Long f_id) throws Exception {
        Food  food=findFoodById(f_id);
        food.setAvailable(!food.isAvailable());
        return foodRepo.save(food);
    }
}
