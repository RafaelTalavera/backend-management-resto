package com.exioma.backendmanagementresto.dto;

import java.time.LocalDateTime;


public record ReservationRequestDTO(
        int people,
        LocalDateTime dateTime,
        Long boardId,
        Long customerId,
        Long employeeId
) {


}
