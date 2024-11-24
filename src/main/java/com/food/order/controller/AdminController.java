package com.food.order.controller;

import com.food.order.enums.MenuType;
import com.food.order.service.MenuService;
import com.food.order.service.OrderService;
import com.food.order.data.entity.MenuItem;
import com.food.order.data.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MenuService menuService;

    @Autowired
    OrderService orderService;

    @GetMapping()
    String Dashboard(){
        return "admin/dashboard";
    }
    @GetMapping("/menu")
    ModelAndView menu(){
        List<MenuItem> breakfast = menuService.getMenuItemByType(MenuType.BREAKFAST);
        List<MenuItem> lunch = menuService.getMenuItemByType(MenuType.LUNCH);
        List<MenuItem> dinner = menuService.getMenuItemByType(MenuType.DINNER);
        List<MenuItem> dessert = menuService.getMenuItemByType(MenuType.DESSERT);
        List<MenuItem> drinks = menuService.getMenuItemByType(MenuType.DRINKS);
        Map<String, Object> map = new HashMap<>();
        map.put("breakfasts",breakfast);
        map.put("lunches",lunch);
        map.put("dinners",dinner);
        map.put("desserts",dessert);
        map.put("drinks",drinks);
        map.put("item",new MenuItem());
        return new ModelAndView("admin/menu",map);
    }

    @GetMapping("/menu/delete/{id}")
    String deleteItem(@PathVariable(value = "id") String id){
        menuService.deleteMenuItem(id);
        return "redirect:/admin/menu";
    }

    @PostMapping("/menu/add")
    String addItem(@ModelAttribute("item") MenuItem menuItem){
        menuItem.setId(UUID.randomUUID().toString());
        menuService.addMenuItem(menuItem);
        return "redirect:/admin/menu";
    }

    @GetMapping("/order")
    ModelAndView order(){
        List<Order> orders = orderService.getAllOrder();
        Map<String, Object> map = new HashMap<>();
        map.put("orders",orders);
        map.put("order", null);
        return new ModelAndView("admin/order",map);
    }

    @GetMapping("/order/details/{orderId}")
    public ModelAndView orderDetail(@PathVariable("orderId") String orderId){
        List<Order> orders = orderService.getAllOrder();
        Order order = orderService.getOrderById(orderId);
        orders = orders.stream().filter(o -> !(o.equals(order)) ).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("orders",orders);
        map.put("order",order);
        return new ModelAndView("admin/order",map);
    }

    @GetMapping("/order/deliver/{orderId}")
    public String deliverOrder(@PathVariable("orderId") String orderId){
        Order order = orderService.deliverOrder(orderId);
        return "redirect:/admin/order";
    }

}
