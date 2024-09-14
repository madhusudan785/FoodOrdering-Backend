package com.authapi.foodordering.controller;


import com.authapi.foodordering.models.Category;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.services.CategoryService;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/admin/add/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String token) throws Exception{

        User use=userService.findUserByToken(token);
        Category createdCategory=categoryService.createCategory(category.getName(),use.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }
    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String token) throws Exception {

        User use = userService.findUserByToken(token);
        List<Category> createdCategory = categoryService.findCategoryByRestaurantId(use.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }

}
