package com.food.order.service;

import com.food.order.data.repository.FacilityRepository;
import com.food.order.data.repository.StaffRepository;
import com.food.order.data.repository.TestimonyRepository;
import com.food.order.service.interfaces.IAboutService;
import com.food.order.data.entity.Facility;
import com.food.order.data.entity.Staff;
import com.food.order.data.entity.Testimony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService implements IAboutService {
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    FacilityRepository facilityRepository;
    @Autowired
    TestimonyRepository testimonyRepository;
    @Override
    public List<Staff> getStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public List<Testimony> getTestimonies() {
        return testimonyRepository.findAll();
    }

    @Override
    public List<Facility> getFacilities() {
        return facilityRepository.findAll();
    }

}
