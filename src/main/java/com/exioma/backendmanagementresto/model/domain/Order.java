package com.exioma.backendmanagementresto.model.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<ItemsOrder> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")  // Nombre correcto del campo de relación
    private Reservation reservation;  // Corregido el nombre del campo

    public Double getTotal() {
        return items.stream()
                .mapToDouble(ItemsOrder::calculateAmount)
                .sum();
    }

    public void setCustomerAndOrder(Customer customer) {
        this.customer = customer;
        customer.getOrders().add(this);
    }

    public void setEmployeeAndOrder(Employee employee) {
        this.employee = employee;
        employee.getOrders().add(this);
    }

    // Corregir el nombre del método y la lógica para agregar ItemsOrder a la lista
    public void addItemsOrder(ItemsOrder itemsOrder) {
        this.items.add(itemsOrder);
        itemsOrder.setOrder(this);
    }

    private static final long serialVersionUID = 1L;
}
