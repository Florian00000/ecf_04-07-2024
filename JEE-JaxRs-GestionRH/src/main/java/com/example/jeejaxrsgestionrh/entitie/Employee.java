package com.example.jeejaxrsgestionrh.entitie;

import com.example.jeejaxrsgestionrh.entitie.map.EmployeeMapper;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "position_id")
    //@JsonBackReference("position-employee")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "department_id")
    //@JsonBackReference("department-employee")
    private Department department;

    public Employee mapper()  {
        return EmployeeMapper.map(this);
    }

    public Position getPosition() {
        if (position == null) {
            return null;
        }else return position.mapper();
    }

    public Department getDepartment() {
        if (department == null) {
            return null;
        }else return department.mapper();
    }
}
