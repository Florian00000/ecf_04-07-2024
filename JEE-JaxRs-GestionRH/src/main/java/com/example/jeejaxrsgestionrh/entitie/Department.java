package com.example.jeejaxrsgestionrh.entitie;

import com.example.jeejaxrsgestionrh.entitie.map.DepartmentMapper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "department_name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    //@JsonManagedReference("department-employee")
    private List<Employee> employees;

    public Department mapper(){
        return DepartmentMapper.map(this);
    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            return null;
        }else{
            return employees.stream().peek(e -> {
                e.setDepartment(null);
            }).collect(Collectors.toList());
        }
    }
}
