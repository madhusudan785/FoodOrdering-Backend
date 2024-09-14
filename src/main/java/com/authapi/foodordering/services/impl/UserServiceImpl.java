package com.authapi.foodordering.services.impl;

import com.authapi.foodordering.models.User;
import com.authapi.foodordering.repositories.UserRepository;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtServiceImpl jwtService;

    @Override
    public User findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            System.out.println("user not found");
        }
        return user;
    }

    @Override
    public User findUserByToken(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String email = jwtService.extractUsername(token);
        return findUserByEmail(email);
    }
}
