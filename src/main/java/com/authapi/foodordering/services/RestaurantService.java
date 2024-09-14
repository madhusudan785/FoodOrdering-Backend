package com.authapi.foodordering.services;

import com.authapi.foodordering.dto.RestaurantDto;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    Restaurant updateRestaurant(Long r_id,CreateRestaurantRequest request) throws Exception;

    Restaurant getRestaurant(Long r_id)throws Exception;

    List<Restaurant> getAllRestaurants();

    void deleteRestaurant(Long r_id) throws Exception;

    List<Restaurant> searchRestaurantsByName(String keyword);

    Restaurant getRestaurantByUserId(Long u_id)throws Exception;

    RestaurantDto addToFavorites(Long r_id, User user )throws Exception;

    Restaurant restaurantStatus(long r_id)throws Exception;
}
