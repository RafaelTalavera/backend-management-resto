package com.exioma.backendmanagementresto.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "reservations")
@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private int people;

    @NotBlank
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "reservation")
    private List<Order> orders;

    // En la clase Reservation
    public void setCustomerAndReservation(Customer customer) {
        this.customer = customer;
        customer.getReservations().add(this);
    }


    private static final long serialVersionUID = 1L;

}
