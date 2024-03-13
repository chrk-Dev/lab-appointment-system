package com.CIS6003.labAppointmentSystem.mapper;

import com.CIS6003.labAppointmentSystem.rest.dto.UserDto;
import com.CIS6003.labAppointmentSystem.model.User;

public interface UserMapper {

    UserDto toUserDto(User user);
}