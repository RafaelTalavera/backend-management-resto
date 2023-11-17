package com.exioma.backendmanagementresto.dto;

public record MenuRequestDTO(
        String imagen,
        String title,
        String detail,
        Double price) {
}
