package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.Cart;
import com.authapi.foodordering.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
