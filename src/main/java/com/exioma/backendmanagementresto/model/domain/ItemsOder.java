package com.exioma.backendmanagementresto.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items_orders")
@Entity
public class ItemsOder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Double calculateAmount(){
        return amount.doubleValue()* menu.getPrice();
    }

    @Serial
    private static final long serialVersionUID = 1L;

}
