package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Customer;
import com.exioma.backendmanagementresto.model.domain.Order;
import com.exioma.backendmanagementresto.model.domain.Reservation;

import java.util.List;

public record CustomerResponseDTO (
        Long id,
        String name,
        Boolean pago,
        List<Reservation> reservations,
        List<Order> orders
){
    public CustomerResponseDTO(Customer customer){
        this(customer.getId(), customer.getName(), customer.getPago(),
                customer.getReservations(), customer.getOrders());
    }

}
