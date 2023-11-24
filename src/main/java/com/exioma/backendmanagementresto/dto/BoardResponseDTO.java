package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Board;

public record BoardResponseDTO (
        Long id,
        int chair,
        String name,
        Boolean condition,
        ReservationResponseDTO reservation

){
    public BoardResponseDTO(Board board){
        this(board.getId(), board.getChair(), board.getName(), board.getCondition(), new ReservationResponseDTO(board.getReservation()));
    }
}
