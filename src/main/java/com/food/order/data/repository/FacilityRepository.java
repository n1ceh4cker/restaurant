package com.food.order.data.repository;

import com.food.order.data.entity.Facility;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacilityRepository extends MongoRepository<Facility, String> {
}
