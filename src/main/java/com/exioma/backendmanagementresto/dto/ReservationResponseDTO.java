package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Board;
import com.exioma.backendmanagementresto.model.domain.Customer;
import com.exioma.backendmanagementresto.model.domain.Reservation;

import java.time.LocalDateTime;

public record ReservationResponseDTO(
        Long id,
        int people,
        LocalDateTime dateTime,
        Board boardId,
        Customer customerId
){
    public ReservationResponseDTO(Reservation reservation){
        this(reservation.getId(),
                reservation.getPeople(),
                reservation.getDateTime(),
                reservation.getBoard(),
                reservation.getCustomer());
    }
}