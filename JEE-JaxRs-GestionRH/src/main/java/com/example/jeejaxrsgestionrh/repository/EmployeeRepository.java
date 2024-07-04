package com.example.jeejaxrsgestionrh.repository;

import com.example.jeejaxrsgestionrh.entitie.Employee;
import org.hibernate.Session;

import java.util.List;

public class EmployeeRepository extends Repository<Employee> {

    public EmployeeRepository(Session _session) {
        super(_session);
    }

    @Override
    public Employee findById(int id) {
        try {
            return (Employee) _session.get(Employee.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        return _session.createQuery("from Employee", Employee.class).list();
    }
}
