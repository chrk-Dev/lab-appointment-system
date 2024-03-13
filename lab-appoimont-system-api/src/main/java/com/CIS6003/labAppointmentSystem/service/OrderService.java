package com.CIS6003.labAppointmentSystem.service;

import com.CIS6003.labAppointmentSystem.model.Appointment;

import java.util.List;

public interface OrderService {

    List<Appointment> getOrders();

    List<Appointment> getOrdersContainingText(String text);

    Appointment validateAndGetOrder(String id);

    Appointment saveOrder(Appointment appointment);

    void deleteOrder(Appointment appointment);
}
