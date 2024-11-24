package com.food.order.data.repository;

import com.food.order.data.entity.User;
import com.food.order.data.entity.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<VerificationToken, String> {
    VerificationToken findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}
