package com.food.order.service.interfaces;

import com.food.order.data.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    Order getOrderById(String id);
    List<Order> getOrderByUser();
    List<Order> getAllOrder();
    Order confirmOrder();
    Order deliverOrder(String orderId);
}
