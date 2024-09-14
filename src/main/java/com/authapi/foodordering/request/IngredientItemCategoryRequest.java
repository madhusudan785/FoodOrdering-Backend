package com.authapi.foodordering.request;

import lombok.Data;

@Data
public class IngredientItemCategoryRequest {
    private String name;
    Long restaurantId;
}
