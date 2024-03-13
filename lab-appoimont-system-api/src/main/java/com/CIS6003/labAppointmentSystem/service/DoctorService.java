package com.CIS6003.labAppointmentSystem.service;

import com.CIS6003.labAppointmentSystem.model.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    Doctor saveDoctor(Doctor doctor);

}
