package com.food.order.service;

import com.food.order.data.entity.CartItem;
import com.food.order.data.entity.MenuItem;
import com.food.order.data.entity.Order;
import com.food.order.data.repository.MenuRepository;
import com.food.order.data.repository.OrderRepository;
import com.food.order.service.interfaces.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CartService implements ICartService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Order getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> optionalOrder = orderRepository.findByEmailAndOrdered(email, false);
        return optionalOrder.isEmpty()?null:optionalOrder.getFirst();
    }

    @Override
    public Order addToCart(String menuId) throws Exception {
        MenuItem menuItem = menuRepository.findById(menuId)
                .orElseThrow(() -> new Exception("not found"));
        CartItem newItem = CartItem.builder()
                .id(UUID.randomUUID().toString())
                .menuItem(menuItem)
                .quantity(1)
                .build();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> optionalOrder = orderRepository.findByEmailAndOrdered(email, false);
        if(!optionalOrder.isEmpty()){
            Order order = optionalOrder.getFirst();
            List<CartItem> list = order.getItems()
                    .stream().filter((item) -> item.getMenuItem().getId().equals(menuId)).toList();
            if(list.isEmpty()){
                order.getItems().add(newItem);
            }else {
                list.getFirst().setQuantity(list.getFirst().getQuantity()+1);
            }
            order.setItemTotal(order.getItemTotal() + newItem.getMenuItem().getPrice());
            return orderRepository.save(order);
        }else {
            Order order = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .email(email)
                    .items(Collections.singletonList(newItem))
                    .itemTotal(newItem.getMenuItem().getPrice())
                    .orderDate(new Date())
                    .delivered(false)
                    .ordered(false)
                    .build();
            return orderRepository.save(order);
        }

    }

    @Override
    public Order removeToCart(String menuId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> optionalOrder = orderRepository.findByEmailAndOrdered(email, false);
        Order order = optionalOrder.getFirst();
        List<CartItem> list = order.getItems()
                .stream().filter((item) -> item.getMenuItem().getId().equals(menuId)).toList();
        if(list.getFirst().getQuantity()==1){
            order.getItems().remove(list.getFirst());
        }else {
            list.getFirst().setQuantity(list.getFirst().getQuantity()-1);
        }
        order.setItemTotal(order.getItemTotal() - list.getFirst().getMenuItem().getPrice());
        return orderRepository.save(order);

    }

    @Override
    public Order deleteFromCart(String menuId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> optionalOrder = orderRepository.findByEmailAndOrdered(email, false);
        Order order = optionalOrder.getFirst();
        List<CartItem> list = order.getItems()
                .stream().filter((item) -> item.getMenuItem().getId().equals(menuId)).toList();
        order.getItems().remove(list.getFirst());
        order.setItemTotal(order.getItemTotal() - list.getFirst().getMenuItem().getPrice()*list.getFirst().getQuantity());
        return orderRepository.save(order);
    }

}
