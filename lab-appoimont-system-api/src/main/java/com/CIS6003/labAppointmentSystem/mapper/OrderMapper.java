package com.CIS6003.labAppointmentSystem.mapper;

import com.CIS6003.labAppointmentSystem.model.Appointment;
import com.CIS6003.labAppointmentSystem.rest.dto.CreateOrderRequest;
import com.CIS6003.labAppointmentSystem.rest.dto.OrderDto;

public interface OrderMapper {

    Appointment toOrder(CreateOrderRequest createOrderRequest);

    OrderDto toOrderDto(Appointment appointment);
}