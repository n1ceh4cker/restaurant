package com.food.order.service.interfaces;

import com.food.order.enums.MenuType;
import com.food.order.data.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMenuService {
    public List<MenuItem> getMenuItemByType(MenuType menuType);
    public List<MenuItem> getMenuItem();
    public MenuItem addMenuItem(MenuItem menuItem);
    public MenuItem updateMenuItem(MenuItem menuItem, String id) throws Exception;
    public void deleteMenuItem(String id);
}
