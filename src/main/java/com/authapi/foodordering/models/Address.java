package com.authapi.foodordering.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String country;

    public void updateFrom(Address newAddress) {
        if (newAddress != null) {
            this.setStreetAddress(newAddress.getStreetAddress());
            this.setCity(newAddress.getCity());
            this.setStateProvince(newAddress.getStateProvince());
            this.setCountry(newAddress.getCountry());
        }
    }

}
