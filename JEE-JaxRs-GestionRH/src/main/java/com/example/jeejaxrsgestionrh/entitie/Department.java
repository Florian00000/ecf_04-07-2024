package com.example.jeejaxrsgestionrh.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "department_name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Employee> employees;
}
