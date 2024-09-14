package com.authapi.foodordering.controller;

import com.authapi.foodordering.models.IngredientItemCategory;
import com.authapi.foodordering.models.IngredientsItem;
import com.authapi.foodordering.request.IngredientItemCategoryRequest;
import com.authapi.foodordering.request.IngredientItemRequest;
import com.authapi.foodordering.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientItemCategory>  createIngredientItemCategory(@RequestBody IngredientItemCategoryRequest request) throws Exception {
        IngredientItemCategory item=ingredientsService.createIngredientItemCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<IngredientsItem>  createIngredientItem(@RequestBody IngredientItemRequest request) throws Exception {
        IngredientsItem item=ingredientsService.createIngredientsItems(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem>  updateIngredientsStock(@PathVariable Long id) throws Exception {
        IngredientsItem item=ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>>  getRestaurantIngredients(@PathVariable Long id) throws Exception {
        List<IngredientsItem> item=ingredientsService.findIngredientsItemByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientItemCategory>>  getRestaurantCategory(@PathVariable Long id) throws Exception {
        List<IngredientItemCategory> item=ingredientsService.findIngredientItemCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


}
