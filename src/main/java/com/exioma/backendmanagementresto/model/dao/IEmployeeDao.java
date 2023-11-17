package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeDao extends CrudRepository<Employee, Long> {
}
