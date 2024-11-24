package com.food.order.controller;

import com.food.order.service.interfaces.IAboutService;
import com.food.order.data.entity.Facility;
import com.food.order.data.entity.Staff;
import com.food.order.data.entity.Testimony;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/about")
public class AboutController {
    @Autowired
    IAboutService aboutService;
    @GetMapping()
    public String about(Model model){
        List<Staff> staffs = aboutService.getStaffs();
        List<Testimony> testimonies = aboutService.getTestimonies();
        List<Facility> facilities = aboutService.getFacilities();
        model.addAttribute("staffs", staffs);
        model.addAttribute("testimonies", testimonies);
        model.addAttribute("facilities", facilities);
        return "about";
    }
}
