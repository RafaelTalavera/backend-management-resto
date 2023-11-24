package com.exioma.backendmanagementresto.model.domain;


import com.exioma.backendmanagementresto.dto.EmployeeRequestDTO;
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

    private Date brithday;

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

    public Employee(EmployeeRequestDTO requestDTO) {
        this.brithday = requestDTO.brithDay();
        this.name = requestDTO.name();
        this.DateAdmission = requestDTO.dateAdmission();
        this.position = requestDTO.position();
    }

    private static final long serialVersionUID = 1L;


}
