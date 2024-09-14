package com.authapi.foodordering.controller;

import com.authapi.foodordering.models.Cart;
import com.authapi.foodordering.models.Role;
import com.authapi.foodordering.models.User;
import com.authapi.foodordering.repositories.CartRepository;
import com.authapi.foodordering.repositories.UserRepository;
import com.authapi.foodordering.response.AuthResponse;
import com.authapi.foodordering.request.LoginRequest;
import com.authapi.foodordering.services.CustomUserDetailService;
import com.authapi.foodordering.services.impl.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        // Log incoming request
        System.out.println("Received signup request: " + user);

        User isExistingUser = userRepository.findByEmail(user.getEmail());
        if(isExistingUser != null){
            throw new Exception("Email is already registered");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());
        System.out.println("user name"+newUser.getFullName());
        newUser.setAddress(user.getAddress());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());

        // Log the new user before saving
        System.out.println("Saving new user: " + newUser);

        User savedUser = userRepository.save(newUser);
        log.debug("User saved successfully with email: " + newUser.getEmail());
        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate JWT
        String jwt = JwtServiceImpl.generateToken(user);

        // Log the JWT
        System.out.println("Generated JWT: " + jwt);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful");
        authResponse.setRole(savedUser.getRole());

        // Log the response
        System.out.println("Returning response: " + authResponse);

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest request) throws Exception {
        String userName = request.getEmail();
        System.out.println("sigin"+userName);
        String password = request.getPassword();

        Authentication authentication=authenticate(userName,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        User user = userRepository.findByEmail(userName);

        String jwt=JwtServiceImpl.generateToken(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Sign-in successful");

            authResponse.setRole(Role.valueOf(role));
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails=customUserDetailService.loadUserByUsername(userName);
        System.out.println(userDetails);
        if(userDetails==null){
            throw new UsernameNotFoundException("User not found");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
