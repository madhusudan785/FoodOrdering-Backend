package com.authapi.foodordering.controller;

import com.authapi.foodordering.models.Food;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.services.FoodService;
import com.authapi.foodordering.services.RestaurantService;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/food")
public class FoodController {

    @Autowired
    private  FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> createFood(@RequestParam String query,
                                           @RequestHeader("Authorization") String token) throws Exception {

        User user=userService.findUserByToken(token);

        List<Food> food=foodService.searchFood(query);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{r_id}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long r_id,
                                                 @RequestParam boolean vegetarian,
                                                 @RequestParam boolean nonVegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam(required = false) String category,
                                                 @RequestHeader("Authorization") String token) throws Exception {

        User user=userService.findUserByToken(token);

        List<Food> food=foodService.getRestaurantFood(r_id,vegetarian,nonVegetarian,seasonal,category);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }


}
