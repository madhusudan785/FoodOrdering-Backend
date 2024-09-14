package com.authapi.foodordering.models;

import com.authapi.foodordering.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    private int totalItem;
    private int totalPrice;

    @ManyToOne
    private User customer ;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Address deliverAddress;

    @OneToMany
    private List<OrderItem> items;
//    List<PaymentMethod> paymentMethods

}
