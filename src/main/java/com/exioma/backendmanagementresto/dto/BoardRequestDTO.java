package com.exioma.backendmanagementresto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardRequestDTO(
        @NotBlank int chair,
        @NotBlank String name,
        @NotNull Boolean condition
) {

}