package com.authapi.foodordering.controller;


import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.request.CreateRestaurantRequest;
import com.authapi.foodordering.response.MessageResponse;
import com.authapi.foodordering.services.RestaurantService;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String token) {

        User user=userService.findUserByToken(token);
        Restaurant restaurant=restaurantService.createRestaurant(request,user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest request,
                                                       @RequestHeader("Authorization") String token,
                                                       @PathVariable Long id) throws Exception {

        User user=userService.findUserByToken(token);
        Restaurant restaurant=restaurantService.updateRestaurant(id,request);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {
        User user=userService.findUserByToken(token);
        restaurantService.deleteRestaurant(id);
        MessageResponse message=new MessageResponse();
        message.setMessage("Restaurant deleted");
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long id,
                                                             @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.findUserByToken(token);
        Restaurant restaurant = restaurantService.restaurantStatus(id);
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    @GetMapping("/user/restaurant")
    public ResponseEntity<Restaurant> getRestaurantByUserId(
            @RequestHeader("Authorization") String token) throws Exception {


            User user=userService.findUserByToken(token);
            Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
            return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
}
