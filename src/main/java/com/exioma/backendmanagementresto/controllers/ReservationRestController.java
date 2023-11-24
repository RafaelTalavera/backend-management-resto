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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

    @Autowired private IReservationService reservationService;

    @Autowired private IBoardService boardService;

    @Autowired private ICustomerService customerService;

    @Autowired private IEmployeeService employeeService;

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
        Customer customer = customerService.findById(data.customerId());
        Employee employee = employeeService.findById(data.employeeId());

        Board board = boardService.findById(data.boardId());

        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reservation reservationData = new Reservation(data);
        reservationData.setBoard(board);
        reservationData.setCustomer(customer);
        reservationData.setEmployee(employee);
        reservationService.save(reservationData);
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(reservationData);

        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationRequestDTO data) {

        Reservation existingReservation = reservationService.finById(id);

        if (existingReservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerService.findById(data.customerId());
        Employee employee = employeeService.findById(data.employeeId());

        Board board = boardService.findById(data.boardId());

        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingReservation.setPeople(data.people());
        existingReservation.setDateTime(data.dateTime());
        existingReservation.setBoard(board);
        existingReservation.setCustomer(customer);
        existingReservation.setEmployee(employee);
        reservationService.save(existingReservation);
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO(existingReservation);

        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id){
        reservationService.deleteById(id);
        return ResponseEntity.ok("Éxito: La reservación con ID " + id + " ha sido eliminado.");
    }

}



