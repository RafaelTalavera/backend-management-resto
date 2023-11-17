package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface IOrderDao extends CrudRepository<Order, Long> {
}
