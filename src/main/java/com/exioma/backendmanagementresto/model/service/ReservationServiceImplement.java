package com.exioma.backendmanagementresto.model.service;

import com.exioma.backendmanagementresto.model.dao.IReservationDao;
import com.exioma.backendmanagementresto.model.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImplement implements IReservationService{

    @Autowired
    private IReservationDao reservationDao;

    @Transactional(readOnly = true)
    @Override
    public List<Reservation> findAll() {return (List<Reservation>) reservationDao.findAll();}

    @Transactional
    @Override
    public void save(Reservation Reservation) { reservationDao.save(Reservation);}

    @Transactional
    @Override
    public Reservation finById(Long id) { return reservationDao.findById(id).orElse(null);}

    @Transactional
    @Override
    public void deleteById(Long id) {reservationDao.deleteById(id);}
}
