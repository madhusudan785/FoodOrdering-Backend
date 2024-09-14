package com.authapi.foodordering.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Embeddable
public class RestaurantDto {
    Long id;
    String title;

    @Column(length = 1000)
    List<String> images;

    String description;


}
