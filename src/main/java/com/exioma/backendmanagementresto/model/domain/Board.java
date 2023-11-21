package com.exioma.backendmanagementresto.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "boards")
@Entity
public class Board implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private int chair;

    @NotBlank
    private String name;

    @NotNull
    private Boolean condition;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @OneToMany(mappedBy = "board")
    private List<Order> orders;

    public Board(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static final long serialVersionUID = 1L;
}
