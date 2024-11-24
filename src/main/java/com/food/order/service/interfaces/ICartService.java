package com.food.order.service.interfaces;

import com.food.order.data.entity.Order;

public interface ICartService {
    Order getCart();
    Order addToCart(String menuId) throws Exception;
    Order removeToCart(String menuId);
    Order deleteFromCart(String menuId);
}
