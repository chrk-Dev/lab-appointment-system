package com.CIS6003.labAppointmentSystem.mapper;

import com.CIS6003.labAppointmentSystem.rest.dto.UserDto;
import com.CIS6003.labAppointmentSystem.model.Appointment;
import com.CIS6003.labAppointmentSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        List<UserDto.OrderDto> orders = user.getAppointments().stream().map(this::toUserDtoOrderDto).toList();
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole(), orders);
    }

    private UserDto.OrderDto toUserDtoOrderDto(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        return new UserDto.OrderDto(appointment.getId(), appointment.getDescription(), appointment.getCreatedAt());
    }
}
