package com.food.order.controller;

import com.food.order.data.entity.*;
import com.food.order.dto.UserDto;
import com.food.order.service.interfaces.IAboutService;
import com.food.order.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("")
@Slf4j
public class UserController {
    @Autowired
    IUserService service;
    @Autowired
    IAboutService aboutService;
    @GetMapping(value = { "/", "/index"})
    public String index(Model model){
        List<Staff> staffs = aboutService.getStaffs();
        List<Testimony> testimonies = aboutService.getTestimonies();
        List<Facility> facilities = aboutService.getFacilities();
        model.addAttribute("staffs", staffs);
        model.addAttribute("testimonies", testimonies);
        model.addAttribute("facilities", facilities);
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String getRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUserAccount(@ModelAttribute("user")  UserDto userDto, Model model, HttpServletRequest request) throws Exception {
        String[] origins = request.getRequestURL().toString().split("/");
        String origin = origins[0]+ "//" + origins[2];
        User registered = service.registerUser(userDto, origin);
        if(registered==null){
            model.addAttribute("message", "Email is already registered!!");
            model.addAttribute("user", userDto);
        }else {
            model.addAttribute("message","An email has been sent to you!!");
            model.addAttribute("user",new UserDto());
        }
        return "signup";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(Model model, @RequestParam("token") String token){
        VerificationToken verificationToken = service.getVerificationToken(token);
        if(verificationToken == null){
            model.addAttribute("message", "The token is invalid. Please signup again.");
            model.addAttribute("expired",false);
            return "badUser";
        }
        if(verificationToken.getExpiryTime().getTime()-new Date().getTime()<=0){
            model.addAttribute("message","The token is expired. Please resend token.");
            model.addAttribute("expired",true);
            model.addAttribute("token",token);
            return "badUser";
        }
        User user = verificationToken.getUser();
        service.enableRegisteredUser(user, verificationToken);
        return "redirect:/login";
    }

    @GetMapping("/resendToken")
    public String resendToken(@RequestParam("token") String token, HttpServletRequest request){
        String[] origins = request.getRequestURL().toString().split("/");
        String origin = origins[0]+ "//" + origins[2];
        VerificationToken newToken = service.extendVerificationToken(token);
        CompletableFuture.runAsync(() -> service.sendEmail(newToken, origin));
        return "redirect:/login";
    }
}
