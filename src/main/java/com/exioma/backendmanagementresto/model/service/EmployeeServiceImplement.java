package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IEmployeeDao;
import com.exioma.backendmanagementresto.model.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplement implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
      employeeDao.deleteById(id);
    }
}
