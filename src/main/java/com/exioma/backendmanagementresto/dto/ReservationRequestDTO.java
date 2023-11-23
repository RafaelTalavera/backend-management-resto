package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Board;
import com.exioma.backendmanagementresto.model.domain.Customer;
import com.exioma.backendmanagementresto.model.domain.Order;

import java.time.LocalDateTime;

public record ReservationRequestDTO(
        int people,
        LocalDateTime dateTime,
        Long boardId,
        Long customerId,
        Long employeeId
) {

    public Long getBoardId() {
        return boardId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
