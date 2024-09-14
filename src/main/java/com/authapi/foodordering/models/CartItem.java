package com.authapi.foodordering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity ;
    private Long totalPrice ;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ElementCollection
    private List<String> ingredients;

    @ManyToOne
    private Food food;
}
