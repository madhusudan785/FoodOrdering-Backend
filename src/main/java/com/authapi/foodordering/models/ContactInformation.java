package com.authapi.foodordering.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactInformation {

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("tweeter")
    private String tweeter;

    @JsonProperty("instagram")
    private String instagram;

}
