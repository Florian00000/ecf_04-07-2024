package com.example.jeejaxrsgestionrh.controller;

import com.example.jeejaxrsgestionrh.entitie.Employee;
import com.example.jeejaxrsgestionrh.entitie.Position;
import com.example.jeejaxrsgestionrh.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Inject
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream().peek(e -> {
            e.setPosition(e.getPosition());
            e.setDepartment(e.getDepartment());
        }).collect(Collectors.toList());
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee addEmployee(Employee employee) {
        Employee employeeSend = employeeService.createEmployee(employee);
        employeeSend.setPosition(employeeSend.getPosition());
        employeeSend.setDepartment(employeeSend.getDepartment());
        return employeeSend;
    }

    @GET
    @Path("/{id}")
    public Employee getEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        employee.setPosition(employee.getPosition());
        employee.setDepartment(employee.getDepartment());
        return employee;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean result = employeeService.deleteEmployee(id);
        if (result) {
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        boolean result = employeeService.updateEmployee(id, employee.getPosition(), employee.getDepartment());
        if (result) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/lastname={lastname}")
    public List<Employee> getByLastname(@PathParam("lastname") String lastName) {
        List <Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getLastName().equalsIgnoreCase(lastName))
                .peek(e -> {
                    e.setPosition(e.getPosition());
                    e.setDepartment(e.getDepartment());
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/department={department}")
    public List<Employee> getByDepartment(@PathParam("department") String department) {
        List <Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getDepartment() != null && employee.getDepartment().getName().equalsIgnoreCase(department))
                .peek(e -> {
                    e.setPosition(e.getPosition());
                    e.setDepartment(e.getDepartment());
                }).collect(Collectors.toList());
    }

    @GET
    @Path("/position={position}")
    public List<Employee> getByPosition(@PathParam("position") String position) {
        List <Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getPosition() != null && employee.getPosition().getName().equalsIgnoreCase(position))
                .peek(e -> {
                    e.setPosition(e.getPosition());
                    e.setDepartment(e.getDepartment());
                }).collect(Collectors.toList());
    }

}
