package com.food.order.data.repository;

import com.food.order.data.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
     List<Address> findByEmail(String email);
     Address findByEmailAndSelected(String email, boolean selected);
}
