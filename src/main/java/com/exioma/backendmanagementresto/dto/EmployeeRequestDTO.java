package com.exioma.backendmanagementresto.dto;

import com.exioma.backendmanagementresto.model.domain.Employee;

import java.util.Date;

public record EmployeeRequestDTO(
    String name,
    String rut,
    Date brithDay,
    Date dateAdmission,
    Employee.Position position




) {
}
