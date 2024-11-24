package com.food.order.service.interfaces;

import com.food.order.data.entity.Facility;
import com.food.order.data.entity.Staff;
import com.food.order.data.entity.Testimony;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAboutService {
    List<Staff> getStaffs();
    List<Testimony> getTestimonies();
    List<Facility> getFacilities();
}
