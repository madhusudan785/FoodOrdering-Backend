package com.authapi.foodordering.request;

import com.authapi.foodordering.models.Category;
import com.authapi.foodordering.models.IngredientsItem;
import lombok.Data;

import java.util.List;
@Data
public class CreateFoodRequest {
    private String foodName;

    private String foodDescription;

    private int foodPrice;

    private List<String> foodImage;

    private Category category;

    private Long restaurantId;

    private boolean favorite;

    private boolean vegetarian;

    private boolean seasonal;

    private List<IngredientsItem> ingredients;
}
