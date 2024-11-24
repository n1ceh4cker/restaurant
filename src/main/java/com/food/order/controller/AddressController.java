package com.food.order.controller;

import com.food.order.service.interfaces.IAddressService;
import com.food.order.data.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/address")
public class AddressController {
    @Autowired
    IAddressService addressService;
    @GetMapping("/get")
    public String getAddress(Model model){
        model.addAttribute("address",new Address());
        model.addAttribute("addresses",addressService.getAddress());
        return "address";
    }

    @PostMapping("/add")
    public String addAddress(@ModelAttribute(value = "address") Address address, Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setSelected(false);
        address.setEmail(email);
        addressService.addAddress(address);
        return "redirect:/address/get";
    }

    @GetMapping("/select")
    public String selectAddress(@RequestParam("addressId") String addressId, Model model){
        addressService.selectAddress(addressId);
        return "redirect:/address/get";

    }

    @GetMapping("/delete")
    public String deleteAddress(@RequestParam("addressId") String addressId, Model model){
        addressService.deleteAddress(addressId);
        return "redirect:/address/get";

    }
}
