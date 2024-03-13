package com.CIS6003.labAppointmentSystem.rest;

import com.CIS6003.labAppointmentSystem.model.Doctor;
import com.CIS6003.labAppointmentSystem.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/doctor")
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping
    public List<Doctor> getDoctor() {
        return doctorService.getAllDoctors();
    }
}