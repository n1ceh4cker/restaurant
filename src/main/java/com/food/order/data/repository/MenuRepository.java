package com.food.order.data.repository;

import com.food.order.enums.MenuType;
import com.food.order.data.entity.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<MenuItem, String> {
    List<MenuItem> findByMenuType(MenuType type);
}
