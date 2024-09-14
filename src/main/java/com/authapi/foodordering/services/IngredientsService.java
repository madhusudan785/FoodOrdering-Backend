package com.authapi.foodordering.services;

import com.authapi.foodordering.models.IngredientItemCategory;
import com.authapi.foodordering.models.IngredientsItem;

import java.util.List;

public interface IngredientsService {
    IngredientItemCategory createIngredientItemCategory(String name,Long restaurantId)throws Exception;

    List<IngredientItemCategory> findIngredientItemCategoryByRestaurantId(Long restaurantId)throws Exception;
    IngredientItemCategory findIngredientItemCategoryById(Long id)throws Exception;
    IngredientsItem createIngredientsItems(Long restaurantId,String ingredientName,Long categoryId)throws Exception;

    List<IngredientsItem> findIngredientsItemByRestaurantId(Long restaurantId)throws Exception;
    IngredientsItem updateStock(Long id)throws Exception;


}
