package com.exioma.backendmanagementresto.model.domain;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String rut;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Order> orders;


    public enum Position { MOZO, RECEPTIONIST, MANAGER, CHEEF }

    @Column(name = "date_admission")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateAdmission;

    public Employee(Long id, String name, Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public List<Order> getOrders() {
        return orders;
    }

    private static final long serialVersionUID = 1L;


}
