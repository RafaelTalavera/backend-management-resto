package com.exioma.backendmanagementresto.model.domain;

import com.exioma.backendmanagementresto.dto.MenuRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menues")
@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imagen;

    @NotBlank
    private String title;

    @NotBlank
    private String detail;

    @NotBlank
    @Positive
    private Double price;

    public Menu(MenuRequestDTO menuRequestDTO) {
        this.imagen = menuRequestDTO.imagen();
        this.title = menuRequestDTO.title();
        this.detail = menuRequestDTO.detail();
        this.price = menuRequestDTO.price();
    }

    private static final long serialVersionUID = 1L;
}
