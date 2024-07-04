package com.example.jeejaxrsgestionrh.repository;

import com.example.jeejaxrsgestionrh.entitie.Department;
import org.hibernate.Session;

import java.util.List;

public class DepartmentRepository extends Repository<Department> {

    public DepartmentRepository(Session _session) {
        super(_session);
    }

    @Override
    public void delete(Department obj) {
            _session.remove(obj);

    }

    @Override
    public Department findById(int id) {
        try{
            return (Department) _session.get(Department.class, id);
        }catch(Exception re){
            return null;
        }

    }

    @Override
    public List<Department> findAll() {
        return _session.createQuery("from Department", Department.class).list();
    }
}
