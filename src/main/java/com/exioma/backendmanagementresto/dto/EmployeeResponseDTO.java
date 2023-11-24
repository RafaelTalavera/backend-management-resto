package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Employee;

import java.util.Date;

public record EmployeeResponseDTO(
        Long id,
        String name,
        String rut,
        Date birthDay,
        Employee.Position position,
        Date dateAdmission
) {
    public EmployeeResponseDTO(Employee employee) {
        this(employee.getId(),
                employee.getName(),
                employee.getRut(),
                employee.getBrithday(),
                employee.getPosition(),
                employee.getDateAdmission());
    }
}
