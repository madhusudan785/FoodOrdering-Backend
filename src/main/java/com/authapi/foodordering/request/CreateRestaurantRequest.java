package com.authapi.foodordering.request;

import com.authapi.foodordering.models.Address;
import com.authapi.foodordering.models.ContactInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {


    private Long restaurantId;

    private String restaurantName;

    private String restaurantDescription;

    private String cuisineType;

    private Address restaurantAddress;

    private ContactInformation contactInformation;

    private String openingHours;

    private List<String> restaurantImages;

}
