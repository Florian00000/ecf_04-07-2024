package com.example.jeejaxrsgestionrh.entitie;

import com.example.jeejaxrsgestionrh.entitie.map.PositionMapper;
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
public class Position {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_position")
    private String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER , cascade = CascadeType.DETACH)
    //@JsonManagedReference("position-employee")
    private List<Employee> employees;

    public Position mapper(){
        return PositionMapper.map(this);
    }

    public List<Employee> getEmployees() {
        if(employees==null){
            return null;
        }else{
            return employees.stream().peek(e -> {
                e.setPosition(null);
            }).collect(Collectors.toList());
        }
    }
}
