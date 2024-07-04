package com.example.jeejaxrsgestionrh.service;

import com.example.jeejaxrsgestionrh.entitie.Employee;
import com.example.jeejaxrsgestionrh.repository.EmployeeRepository;
import com.example.jeejaxrsgestionrh.util.HibernateSession;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@ApplicationScoped
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private SessionFactory sessionFactory;
    private Session session;

    public EmployeeService() {
        this.sessionFactory = HibernateSession.getSessionFactory();
    }

    public List<Employee> getAllEmployees() {
        session = sessionFactory.openSession();
        employeeRepository = new EmployeeRepository(session);
        List<Employee> employees = employeeRepository.findAll();
        session.close();
        return employees;
    }

    public Employee getEmployeeById(int id) {
        session = sessionFactory.openSession();
        employeeRepository = new EmployeeRepository(session);
        Employee employee = employeeRepository.findById(id);
        session.close();
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        employeeRepository = new EmployeeRepository(session);
        employeeRepository.create(employee);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    public boolean deleteEmployee(int id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        employeeRepository = new EmployeeRepository(session);
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            employeeRepository.delete(employee);
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
