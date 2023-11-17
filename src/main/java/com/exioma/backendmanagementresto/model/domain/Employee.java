package com.exioma.backendmanagementresto.model.domain;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String rut;

    @Enumerated(EnumType.STRING)
    private Position position;
    public enum Position { MOZO, RECEPTIONIST, MANAGER, CHEEF }

    @Column(name = "date_admission")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateAdmission;

    @Serial
    private static final long serialVersionUID = 1L;


}
