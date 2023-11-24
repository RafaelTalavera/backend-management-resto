package com.exioma.backendmanagementresto.dto;

public record CustomerRequestDTO (
        String name,
        Boolean pago,
        Long reservationsId,
        Long ordersId

){
}
