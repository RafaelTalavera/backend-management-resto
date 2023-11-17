package com.exioma.backendmanagementresto.model.dao;

import com.exioma.backendmanagementresto.model.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface IReservationDao extends CrudRepository<Reservation, Long> {
}
