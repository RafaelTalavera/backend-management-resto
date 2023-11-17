package com.exioma.backendmanagementresto.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Oder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
