package com.exioma.backendmanagementresto.controllers;


import com.exioma.backendmanagementresto.dto.EmployeeRequestDTO;
import com.exioma.backendmanagementresto.dto.EmployeeResponseDTO;
import com.exioma.backendmanagementresto.exeption.RegistroNoEncontradoException;
import com.exioma.backendmanagementresto.model.domain.Employee;
import com.exioma.backendmanagementresto.model.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    @Autowired private IEmployeeService employeeService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        List<EmployeeResponseDTO> employeeList = employeeService.findAll().stream().map(EmployeeResponseDTO::new)
                .toList();
        if (!employeeList.isEmpty()) {
            return employeeList;
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeService.findById(id));

        if (employeeOptional.isPresent()) {
            EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(employeeOptional.get());
            return ResponseEntity.ok(employeeResponseDTO);
        } else {
            throw new RegistroNoEncontradoException("No se encontró el registro con ID: " + id);
        }
    }


    @CrossOrigin (origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody EmployeeRequestDTO data){
        Employee employeeData = new Employee(data);
        employeeService.save(employeeData);
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(employeeData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public String editEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO data) {
        Optional<Employee> existingEmployee = Optional.ofNullable(employeeService.findById(id));
        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();
            employeeToUpdate.setName(data.name());
            employeeToUpdate.setRut(data.rut());
            employeeToUpdate.setBrithday(data.brithDay());
            employeeService.save(employeeToUpdate);
            return "Éxito: El alimento con ID " + id + " ha sido actualizado.";
        } else {
            return "Error: No se encontró el alimento con ID " + id + ".";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> employeeCustomer(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("Éxito: El alimento con ID " + id + " ha sido eliminado.");
    }


}
