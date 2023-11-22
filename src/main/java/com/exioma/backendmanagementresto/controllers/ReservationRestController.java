package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.ReservationResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.dao.IReservationDao;
import com.exioma.backendmanagementresto.model.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

    @Autowired
    private IReservationService reservationService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ReservationResponseDTO> getAll() {

        List<ReservationResponseDTO> responseList = reservationService.findAll()
                .stream().map(ReservationResponseDTO::new).toList();

        if (!responseList.isEmpty()) {
            return responseList;
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }
}
