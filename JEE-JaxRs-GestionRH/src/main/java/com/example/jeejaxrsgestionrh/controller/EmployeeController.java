package com.example.jeejaxrsgestionrh.controller;

import com.example.jeejaxrsgestionrh.entitie.Employee;
import com.example.jeejaxrsgestionrh.entitie.Position;
import com.example.jeejaxrsgestionrh.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
        return employeeService.getAllEmployees();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee addEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GET
    @Path("/{id}")
    public Employee getEmployee(@PathParam("id") int id) {
        return employeeService.getEmployeeById(id);        
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
}
