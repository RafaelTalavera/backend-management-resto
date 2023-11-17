package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.domain.Customer;

import java.util.List;

public interface ICustomerService {

    public List<Customer> findAll();

    public void save(Customer customer);

    public Customer finOne(Long id);

    public void delete(Long id);
}
