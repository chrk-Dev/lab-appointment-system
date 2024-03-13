package com.CIS6003.labAppointmentSystem.rest;

import com.CIS6003.labAppointmentSystem.mapper.OrderMapper;
import com.CIS6003.labAppointmentSystem.model.Appointment;
import com.CIS6003.labAppointmentSystem.model.User;
import com.CIS6003.labAppointmentSystem.rest.dto.CreateOrderRequest;
import com.CIS6003.labAppointmentSystem.rest.dto.OrderDto;
import com.CIS6003.labAppointmentSystem.security.CustomUserDetails;
import com.CIS6003.labAppointmentSystem.service.OrderService;
import com.CIS6003.labAppointmentSystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.CIS6003.labAppointmentSystem.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(value = "text", required = false) String text) {
        List<Appointment> appointments = (text == null) ? orderService.getOrders() : orderService.getOrdersContainingText(text);
        return appointments.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDto createOrder(@AuthenticationPrincipal CustomUserDetails currentUser,
                                @Valid @RequestBody CreateOrderRequest createOrderRequest) {
        User user = userService.validateAndGetUserByUsername(currentUser.getUsername());
        Appointment appointment = orderMapper.toOrder(createOrderRequest);
        appointment.setId(RandomStringUtils.randomNumeric(4));
        appointment.setUser(user);
        return orderMapper.toOrderDto(orderService.saveOrder(appointment));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public OrderDto deleteOrders(@PathVariable String id) {
        Appointment appointment = orderService.validateAndGetOrder(id.toString());
        orderService.deleteOrder(appointment);
        return orderMapper.toOrderDto(appointment);
    }
}
