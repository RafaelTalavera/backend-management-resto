package com.exioma.backendmanagementresto.model.service;


import com.exioma.backendmanagementresto.model.domain.Reservation;

import java.util.List;

public interface IReservationService {

    public List<Reservation> findAll();

    public void save(Reservation Reservation);

    public Reservation finById(Long id);

    public void deleteById(Long id);
}
