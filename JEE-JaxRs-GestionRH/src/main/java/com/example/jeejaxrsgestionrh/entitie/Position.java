package com.example.jeejaxrsgestionrh.entitie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Position {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_position")
    private String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER , cascade = CascadeType.DETACH)
    @JsonManagedReference("position-employee")
    private List<Employee> employees;
}
