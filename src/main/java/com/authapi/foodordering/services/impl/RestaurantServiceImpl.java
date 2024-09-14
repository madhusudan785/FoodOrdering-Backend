package com.authapi.foodordering.services.impl;

import com.authapi.foodordering.dto.RestaurantDto;
import com.authapi.foodordering.models.Address;
import com.authapi.foodordering.models.Restaurant;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.repositories.AddressRepo;
import com.authapi.foodordering.repositories.RestaurantRepo;
import com.authapi.foodordering.repositories.UserRepository;
import com.authapi.foodordering.request.CreateRestaurantRequest;
import com.authapi.foodordering.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {

        Address address = request.getRestaurantAddress();

        address = addressRepo.save(address);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getRestaurantName());
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getRestaurantDescription());
        restaurant.setImages(request.getRestaurantImages());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

       return restaurantRepo.save(restaurant);


    }

    @Override
    public Restaurant updateRestaurant(Long r_id, CreateRestaurantRequest request) throws Exception {
        Restaurant restaurant =getRestaurant(r_id);

        restaurant.setName(request.getRestaurantName());
        restaurant.setDescription(request.getRestaurantDescription());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setImages(request.getRestaurantImages());
        Address currentAddress = restaurant.getAddress();
        if (currentAddress != null) {
            currentAddress.updateFrom(request.getRestaurantAddress());
            addressRepo.save(currentAddress);
        }


        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long r_id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepo.findById(r_id);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found with id "+r_id);
        }
        return restaurant.get();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    public void deleteRestaurant(Long r_id) throws Exception {
        Restaurant restaurant = getRestaurant(r_id);
        restaurantRepo.delete(restaurant);
    }

    @Override
    public List<Restaurant> searchRestaurantsByName(String keyword) {
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant getRestaurantByUserId(Long u_id) throws Exception {
        Restaurant restaurant = restaurantRepo.findByOwnerId(u_id);
        if (restaurant == null) {
            throw new Exception("Not found with owner id "+u_id);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long r_id, User user) throws Exception {
        Restaurant restaurant=getRestaurant(r_id);

        RestaurantDto restaurantDto=new RestaurantDto();

        restaurantDto.setId(r_id);
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());

      boolean isFavorite=false;
      List<RestaurantDto> favorites=user.getFavorites();
      for (RestaurantDto favourites:favorites) {
          if (favourites.getId().equals(r_id)) {
              isFavorite=true;
              break;
          }
      }
      if (isFavorite) {
          favorites.removeIf(favorite->favorite.getId().equals(r_id));
      }
      else {
          favorites.add(restaurantDto);
      }

        userRepository.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant restaurantStatus(long r_id) throws Exception {
        Restaurant restaurant=getRestaurant(r_id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepo.save(restaurant);
    }
}
