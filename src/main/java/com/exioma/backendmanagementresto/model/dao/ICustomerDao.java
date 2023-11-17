package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerDao extends CrudRepository<Customer, Long> {
}
