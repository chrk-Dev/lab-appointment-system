package com.CIS6003.labAppointmentSystem.repository;

import com.CIS6003.labAppointmentSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Appointment, String> {

    List<Appointment> findAllByOrderByCreatedAtDesc();

    List<Appointment> findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(String id, String description);
}
