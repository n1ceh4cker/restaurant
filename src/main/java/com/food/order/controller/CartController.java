package com.food.order.controller;

import com.food.order.service.interfaces.IAddressService;
import com.food.order.service.interfaces.ICartService;
import com.food.order.data.entity.Address;
import com.food.order.data.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;
    @Autowired
    IAddressService addressService;

    @GetMapping("/get")
    public String cart(Model model){
        Address address = addressService.getSelectedAddress();
        model.addAttribute("order",cartService.getCart());
        model.addAttribute("address",address);
        return "cart";
    }
    @GetMapping("/add")
    public String addCart(@RequestParam(value = "menuId") String menuId, Model model) throws Exception {
        Order order = cartService.addToCart(menuId);
        model.addAttribute("order",order);
        return "redirect:/cart/get";
    }

    @GetMapping("/remove")
    public String removeToCart(@RequestParam(value = "menuId") String menuId, Model model){
        Order order = cartService.removeToCart(menuId);
        model.addAttribute("order",order);
        return "redirect:/cart/get";
    }

    @GetMapping("/delete")
    public String deleteFromCart(@RequestParam(value = "menuId") String menuId, Model model){
        Order order = cartService.deleteFromCart(menuId);
        model.addAttribute("order",order);
        return "redirect:/cart/get";
    }
}
