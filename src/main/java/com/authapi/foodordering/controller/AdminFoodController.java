package com.authapi.foodordering.controller;

import com.authapi.foodordering.models.Food;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.request.CreateFoodRequest;
import com.authapi.foodordering.response.MessageResponse;
import com.authapi.foodordering.services.FoodService;
import com.authapi.foodordering.services.RestaurantService;
import com.authapi.foodordering.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private  FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String token) throws Exception {

        User user=userService.findUserByToken(token);
        Restaurant restaurant=restaurantService.getRestaurant(request.getRestaurantId());
        log.info("Restaurant Id"+request.getRestaurantId());
        System.out.println("restaurant"+restaurant);
        Food food=foodService.createFood(request,request.getCategory(),restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@RequestHeader("Authorization") String token,
                                                      @PathVariable Long id) throws Exception {

        User user=userService.findUserByToken(token);
        foodService.deleteFood(id);
        MessageResponse messageResponse=new MessageResponse();
        messageResponse.setMessage("Food deleted");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
    @PutMapping("/{f_id}")
    public ResponseEntity<Food> updateFoodAvailability(@RequestBody CreateFoodRequest request,
                                                       @RequestHeader("Authorization") String token,
                                                       @PathVariable Long f_id) throws Exception {

        User user=userService.findUserByToken(token);

        Food food=foodService.updateFoodAvailability(f_id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
