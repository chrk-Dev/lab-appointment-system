package com.CIS6003.labAppointmentSystem.service;

import com.CIS6003.labAppointmentSystem.repository.DoctorRepository;
import com.CIS6003.labAppointmentSystem.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;


    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
