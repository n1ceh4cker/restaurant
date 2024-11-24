package com.food.order.controller;

import com.food.order.service.interfaces.IOrderService;
import com.food.order.data.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping("/get")
    public ModelAndView getOrders(){
        List<Order> orders = orderService.getOrderByUser();
        Map<String, Object> map = new HashMap<>();
        map.put("orders",orders);
        map.put("order", null);
        return new ModelAndView("order",map);

    }

    @GetMapping("/details")
    public ModelAndView orderDetail(@PathVariable("orderId") String orderId){
        List<Order> orders = orderService.getOrderByUser();
        Order order = orderService.getOrderById(orderId);
        orders = orders.stream().filter(o -> !(o.equals(order)) ).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("orders",orders);
        map.put("order",order);
        return new ModelAndView("order",map);
    }

    @GetMapping("/confirm")
    public String confirm(){
        orderService.confirmOrder();
        return "redirect:/order/get";
    }
}
