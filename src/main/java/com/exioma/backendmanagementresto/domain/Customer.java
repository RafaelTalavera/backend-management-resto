package com.exioma.backendmanagementresto.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;
import lombok.*;

import java.io.Serializable;



@Table(name="customers")
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean pago;

    @OneToOne(mappedBy = "customer")
    private Reservation reservation;

    @OneToOne(mappedBy = "customer")
    private Order order;

    private static final long serialVersionUID = 1L;

}
