package com.authapi.foodordering.controller;

import com.authapi.foodordering.models.User;
import com.authapi.foodordering.repositories.UserRepository;
import com.authapi.foodordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {

        User user = userService.findUserByToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
