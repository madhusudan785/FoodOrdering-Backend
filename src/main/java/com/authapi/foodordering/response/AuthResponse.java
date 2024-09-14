package com.authapi.foodordering.response;

import com.authapi.foodordering.models.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private Role role;

}
