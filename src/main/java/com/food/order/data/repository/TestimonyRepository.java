package com.food.order.data.repository;

import com.food.order.data.entity.Testimony;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestimonyRepository extends MongoRepository<Testimony, String> {
}
