package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IBoardDao;
import com.exioma.backendmanagementresto.model.dao.ICustomerDao;
import com.exioma.backendmanagementresto.model.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImplement implements ICustomerService{

    @Autowired
    private ICustomerDao customerDao;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
     customerDao.deleteById(id);
    }
}
