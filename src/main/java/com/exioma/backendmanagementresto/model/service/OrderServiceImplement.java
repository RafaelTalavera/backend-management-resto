package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IOrderDao;
import com.exioma.backendmanagementresto.model.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImplement implements IOrderService{

    @Autowired
    private IOrderDao orderDao;

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return (List<Order>) orderDao.findAll();
    }

    @Override
    @Transactional
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order finById(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }
}
