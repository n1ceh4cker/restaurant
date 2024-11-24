package com.food.order.service;

import com.food.order.data.repository.MenuRepository;
import com.food.order.data.repository.OrderRepository;
import com.food.order.service.interfaces.IOrderService;
import com.food.order.data.entity.CartItem;
import com.food.order.data.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    CartService cartService;

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrderByUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = orderRepository.findByEmailAndOrdered(email,true);
        orders.sort(new OrderComparator());
        return orders;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = orderRepository.findByOrdered(true);
        orders.sort(new OrderComparator());
        return orders;
    }


    @Override
    public Order confirmOrder() {
        Order activeOrder = cartService.getCart();
        activeOrder.setAddress(addressService.getSelectedAddress());
        activeOrder.setBill(1.12*activeOrder.getItemTotal()+20);
        activeOrder.setOrdered(true);
        activeOrder.setOrderDate(new Date());
        orderRepository.save(activeOrder);
        return activeOrder;
    }

    @Override
    public Order deliverOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Order deliveredOrder = null;
        if(order != null){
            order.setDeliverDate(new Date());
            order.setDelivered(true);
            deliveredOrder = orderRepository.save(order);
        }
        return deliveredOrder;
    }
}

class OrderComparator implements Comparator<Order>{

    @Override
    public int compare(Order o1, Order o2) {
        return o2.getOrderDate().compareTo(o1.getOrderDate());
    }
}
