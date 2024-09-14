package com.authapi.foodordering.controller;

import com.authapi.foodordering.dto.RestaurantDto;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.services.RestaurantService;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String token,
                                                             @RequestParam String keyWord) {

        User user=userService.findUserByToken(token);
        List<Restaurant> restaurant=restaurantService.searchRestaurantsByName(keyWord);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestHeader("Authorization") String token) {
        User user=userService.findUserByToken(token);
        List<Restaurant> restaurant=restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String token,
            @PathVariable("id") Long id) throws Exception {
        User user=userService.findUserByToken(token);
        Restaurant restaurant=restaurantService.getRestaurant(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}/add-favourite")
    public ResponseEntity<RestaurantDto> addToFavourite(@RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {
        User user=userService.findUserByToken(token);
        RestaurantDto dto=restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }




}
