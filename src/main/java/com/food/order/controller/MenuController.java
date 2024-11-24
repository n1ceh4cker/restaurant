package com.food.order.controller;

import com.food.order.enums.MenuType;
import com.food.order.service.interfaces.IMenuService;
import com.food.order.data.entity.MenuItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService service;
    @GetMapping()
    public ModelAndView menu(){
        List<MenuItem> breakfast = service.getMenuItemByType(MenuType.BREAKFAST);
        List<MenuItem> lunch = service.getMenuItemByType(MenuType.LUNCH);
        List<MenuItem> dinner = service.getMenuItemByType(MenuType.DINNER);
        List<MenuItem> dessert = service.getMenuItemByType(MenuType.DESSERT);
        List<MenuItem> drinks = service.getMenuItemByType(MenuType.DRINKS);
        Map<String, Object> map = new HashMap<>();
        map.put("breakfasts",breakfast);
        map.put("lunches",lunch);
        map.put("dinners",dinner);
        map.put("desserts",dessert);
        map.put("drinks",drinks);
        return new ModelAndView("menu",map);
    }
}
