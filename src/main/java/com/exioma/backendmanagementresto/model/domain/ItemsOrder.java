package com.exioma.backendmanagementresto.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "items_orders")
@Entity
public class ItemsOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
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


    private static final long serialVersionUID = 1L;

}
