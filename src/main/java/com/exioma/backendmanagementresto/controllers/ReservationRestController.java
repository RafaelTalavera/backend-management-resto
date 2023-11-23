package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.ReservationRequestDTO;
import com.exioma.backendmanagementresto.dto.ReservationResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Board;
import com.exioma.backendmanagementresto.model.domain.Customer;
import com.exioma.backendmanagementresto.model.domain.Employee;
import com.exioma.backendmanagementresto.model.domain.Reservation;
import com.exioma.backendmanagementresto.model.service.IBoardService;
import com.exioma.backendmanagementresto.model.service.ICustomerService;
import com.exioma.backendmanagementresto.model.service.IEmployeeService;
import com.exioma.backendmanagementresto.model.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IBoardService boardService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService;

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {

        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationService.finById(id));

        if (reservationOptional.isPresent()) {
            ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(reservationOptional.get());
            return ResponseEntity.ok(reservationResponseDTO);
        } else {
            throw new RegistroNoEncontradoException("No se encontró el registro con ID: " + id);
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> saveReservation(@RequestBody ReservationRequestDTO data) {
        // Crear una nueva instancia de Reservation
        Reservation reservationData = new Reservation(data);

        // Obtener el Board, Customer y Employee por sus IDs (puedes ajustar esto según tu lógica)
        Board board = boardService.findById(data.getBoardId());
        Customer customer = customerService.findById(data.getCustomerId());
        Employee employee = employeeService.findById(data.getEmployeeId());

        // Asignar el Board, Customer y Employee a la reserva
        reservationData.setBoard(board);
        reservationData.setCustomer(customer);
        reservationData.setEmployee(employee);

        // Guardar la reserva en la base de datos
        reservationService.save(reservationData);

        // Crear el DTO de respuesta
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(reservationData);

        // Retornar la respuesta
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.CREATED);
    }

}



