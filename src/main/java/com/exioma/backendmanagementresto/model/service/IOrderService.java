package com.exioma.backendmanagementresto.model.service;


import com.exioma.backendmanagementresto.model.domain.Order;

import java.util.List;

public interface IOrderService {

    public List<Order> findAll();

    public void save(Order order);

    public Order finOne(Long id);

    public void delete(Long id);
}
