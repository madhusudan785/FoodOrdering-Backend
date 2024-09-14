package com.authapi.foodordering.services.impl;

import com.authapi.foodordering.models.IngredientItemCategory;
import com.authapi.foodordering.models.IngredientsItem;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.repositories.IngredientItemCategoryRepo;
import com.authapi.foodordering.repositories.IngredientsItemRepo;
import com.authapi.foodordering.services.IngredientsService;
import com.authapi.foodordering.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private IngredientItemCategoryRepo ingredientItemCategoryRepo;
    @Autowired
    private IngredientsItemRepo ingredientsItemRepo;

    @Override
    public IngredientItemCategory createIngredientItemCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurant(restaurantId);

        IngredientItemCategory category=new IngredientItemCategory();

        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientItemCategoryRepo.save(category);

    }

    @Override
    public List<IngredientItemCategory> findIngredientItemCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.getRestaurant(restaurantId);
        return ingredientItemCategoryRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItemCategory findIngredientItemCategoryById(Long id) throws Exception {
        Optional<IngredientItemCategory> category=ingredientItemCategoryRepo.findById(id);
        if (category.isEmpty()){
            throw new Exception("Ingredient Item Category Not Found");
        }
        return category.get();
    }

    @Override
    public IngredientsItem createIngredientsItems(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurant(restaurantId);
        IngredientItemCategory ingredientItemCategory=findIngredientItemCategoryById(categoryId);

        IngredientsItem ingredientsItem=new IngredientsItem();

        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setName(ingredientName);
        ingredientsItem.setCategory(ingredientItemCategory);

        IngredientsItem item=ingredientsItemRepo.save(ingredientsItem);
        ingredientItemCategory.getIngredients().add(item);

        return item;
    }

    @Override
    public List<IngredientsItem> findIngredientsItemByRestaurantId(Long restaurantId) throws Exception {

        return ingredientsItemRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItem=ingredientsItemRepo.findById(id);
        if (ingredientsItem.isEmpty()){
            throw new Exception("Ingredient Item Not Found");
        }
        IngredientsItem item=ingredientsItem.get();
        item.setInStock(!item.isInStock());
        return item;

    }
}
