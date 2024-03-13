package com.CIS6003.labAppointmentSystem.service;

import com.CIS6003.labAppointmentSystem.exception.OrderNotFoundException;
import com.CIS6003.labAppointmentSystem.repository.OrderRepository;
import com.CIS6003.labAppointmentSystem.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Appointment> getOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Appointment> getOrdersContainingText(String text) {
        return orderRepository.findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(text, text);
    }

    @Override
    public Appointment validateAndGetOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %s not found", id)));
    }

    @Override
    public Appointment saveOrder(Appointment appointment) {
        return orderRepository.save(appointment);
    }

    @Override
    public void deleteOrder(Appointment appointment) {
        orderRepository.delete(appointment);
    }
}
