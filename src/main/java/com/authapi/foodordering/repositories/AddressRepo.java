package com.authapi.foodordering.repositories;

import com.authapi.foodordering.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
