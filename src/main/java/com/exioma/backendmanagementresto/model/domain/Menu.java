package com.exioma.backendmanagementresto.model.domain;

import com.exioma.backendmanagementresto.dto.MenuRequestDTO;
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
@Table(name = "menues")
@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String title;

    private String detail;

    private Double price;

   /* @Enumerated(EnumType.STRING)
    private Tipe tipe;
    public enum Tipe{ DESSERTS, STARTER , MAIN_COURSE, DRINK }
    */

    public Menu(MenuRequestDTO menuRequestDTO) {
        this.imagen = menuRequestDTO.imagen();
        this.title = menuRequestDTO.title();
        this.detail = menuRequestDTO.detail();
        this.price = menuRequestDTO.price();
    }
    @Serial
    private static final long serialVersionUID = 1L;
}
