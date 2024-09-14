package com.authapi.foodordering.services;

import com.authapi.foodordering.models.User;

public interface UserService  {
    User findUserByEmail(String email);
    User findUserByToken(String token);

}
