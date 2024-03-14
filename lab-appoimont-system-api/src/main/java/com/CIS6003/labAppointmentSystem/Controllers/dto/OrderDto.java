package com.CIS6003.labAppointmentSystem.rest.dto;

import java.time.ZonedDateTime;

public record OrderDto(String id, String description, OrderDto.UserDto user, ZonedDateTime createdAt) {

    public record UserDto(String username) {
    }
}