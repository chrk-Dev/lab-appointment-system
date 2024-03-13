package com.CIS6003.labAppointmentSystem.mapper;

import com.CIS6003.labAppointmentSystem.model.Appointment;
import com.CIS6003.labAppointmentSystem.rest.dto.CreateOrderRequest;
import com.CIS6003.labAppointmentSystem.rest.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Appointment toOrder(CreateOrderRequest createOrderRequest) {
        if (createOrderRequest == null) {
            return null;
        }
        return new Appointment(createOrderRequest.getDescription());
    }

    @Override
    public OrderDto toOrderDto(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        OrderDto.UserDto userDto = new OrderDto.UserDto(appointment.getUser().getUsername());
        return new OrderDto(appointment.getId(), appointment.getDescription(), userDto, appointment.getCreatedAt());
    }
}
