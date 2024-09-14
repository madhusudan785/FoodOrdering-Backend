package com.authapi.foodordering.models;

import com.authapi.foodordering.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String fullName;


  @Column(nullable = false, unique = true)
  private String email;
  @Column(unique = true)
  private String phone;
  private String address;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonIgnore//don't need it when ever we fetch the list
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
  private List<Order> orders=new ArrayList<>();

  @ElementCollection
  private List<RestaurantDto>favorites=new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Address> addresses=new ArrayList<>();



}
