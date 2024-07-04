package com.example.jeejaxrsgestionrh.service;

import com.example.jeejaxrsgestionrh.entitie.Department;
import com.example.jeejaxrsgestionrh.repository.DepartmentRepository;
import com.example.jeejaxrsgestionrh.util.HibernateSession;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@ApplicationScoped
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private SessionFactory sessionFactory;
    private Session session;

    public DepartmentService() {
        this.sessionFactory = HibernateSession.getSessionFactory();
    }

    public Department createDepartment(String name){
        session = sessionFactory.openSession();
        session.beginTransaction();
        departmentRepository = new DepartmentRepository(session);
        Department department = Department.builder().name(name).build();
        departmentRepository.create(department);
        session.getTransaction().commit();
        session.close();
        return department;
    }

    public List<Department> getAllDepartments(){
        session = sessionFactory.openSession();
        departmentRepository = new DepartmentRepository(session);
        List<Department> departments = departmentRepository.findAll();
        session.close();
        return departments;
    }

    public Department getDepartmentById(int id){
        session = sessionFactory.openSession();
        departmentRepository = new DepartmentRepository(session);
        Department department = departmentRepository.findById(id);
        session.close();
        return department;
    }

    public boolean deleteDepartmentById(int id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        departmentRepository = new DepartmentRepository(session);
        Department department = departmentRepository.findById(id);
        if(department != null){
            departmentRepository.delete(department);
            session.getTransaction().commit();
            session.close();
        }else{
            session.getTransaction().rollback();
            session.close();
            return false;
        }
        return true;
    }
}
