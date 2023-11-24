package com.exioma.backendmanagementresto.controllers;

import com.exioma.backendmanagementresto.dto.*;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Customer;
import com.exioma.backendmanagementresto.model.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("*api/customer")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> employeeList = customerService.findAll().stream().map(CustomerResponseDTO::new)
                .toList();
        if (!employeeList.isEmpty()) {
            return employeeList;
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = Optional.ofNullable(customerService.findById(id));
        if (customerOptional.isPresent()) {
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customerOptional.get());
            return ResponseEntity.ok(customerResponseDTO);
        } else {
            throw new RegistroNoEncontradoException("No se encontró el registro con ID: " + id);
        }
    }


    @CrossOrigin (origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> saveCostumer(@RequestBody CustomerRequestDTO data){
        Customer customerData = new Customer(data);
        customerService.save(customerData);
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(customerData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public String editCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO data) {
        Optional<Customer> existingCustomer = Optional.ofNullable(customerService.findById(id));
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setName(data.name());
            customerToUpdate.setPago(data.pago());
            customerService.save(customerToUpdate);
            return "Éxito: El alimento con ID " + id + " ha sido actualizado.";
        } else {
            return "Error: No se encontró el alimento con ID " + id + ".";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok("Éxito: El alimento con ID " + id + " ha sido eliminado.");
    }

}


