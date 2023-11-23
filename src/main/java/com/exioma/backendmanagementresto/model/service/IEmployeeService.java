package com.exioma.backendmanagementresto.model.service;


import com.exioma.backendmanagementresto.model.domain.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> findAll();

    public void save(Employee employee);

    public Employee findById(Long id);

    public void deleteById(Long id);
}
