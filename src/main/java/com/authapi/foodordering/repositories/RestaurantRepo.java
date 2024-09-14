package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r FROM Restaurant r  WHERE lower(r.name) LIKE lower(concat('%',:query,'%'))"+
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(long id);
}
