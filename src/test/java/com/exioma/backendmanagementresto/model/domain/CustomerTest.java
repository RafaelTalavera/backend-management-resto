package com.exioma.backendmanagementresto.model.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void testToString() {
        Customer customer = new Customer(1L, "John Doe", true, null, Collections.emptyList());
        assertThat(customer.toString()).isEqualTo("Customer(id=1, name=John Doe, pago=true)");
    }

    @Test
    public void testGetterAndSetter() {
        Customer customer = new Customer();  // Usando el constructor por defecto

        // Set values using setters
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setPago(true);

        // Verify values using getters
        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("John Doe");
        assertThat(customer.getPago()).isEqualTo(true);

        // Test reservations and orders (assuming you have appropriate equals() and hashCode() methods)
        Reservation reservation = new Reservation();
        customer.setReservations(Collections.singletonList(reservation));

        Order order = new Order();
        customer.setOrders(Collections.singletonList(order));

        assertThat(customer.getReservations()).isEqualTo(Collections.singletonList(reservation));
        assertThat(customer.getOrders()).isEqualTo(Collections.singletonList(order));
    }
}