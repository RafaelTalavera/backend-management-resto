package com.exioma.backendmanagementresto.model.domain;

import com.exioma.backendmanagementresto.dto.CustomerRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Table(name="customers")
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Boolean pago;

    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(CustomerRequestDTO customerRequestDTO) {
        this.name = customerRequestDTO.name();
        this.pago = customerRequestDTO.pago();

    }


    @Override
    public String toString() {
        return "Customer(id=" + id + ", name=" + name + ", pago=" + pago + ")";
    }
    private static final long serialVersionUID = 1L;

}
