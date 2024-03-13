package com.CIS6003.labAppointmentSystem.repository;

import com.CIS6003.labAppointmentSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


}
