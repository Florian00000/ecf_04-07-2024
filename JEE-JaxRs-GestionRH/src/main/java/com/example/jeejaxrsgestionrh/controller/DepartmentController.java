package com.example.jeejaxrsgestionrh.controller;

import com.example.jeejaxrsgestionrh.entitie.Department;
import com.example.jeejaxrsgestionrh.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/department")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentController {

    private final DepartmentService departmentService;

    @Inject
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GET
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return departments.stream().peek(department -> {
            department.setEmployees(department.getEmployees());
        }).collect(Collectors.toList());
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Department addDepartment(Department department) {
        return departmentService.createDepartment(department.getName());
    }

    @GET
    @Path("/{id}")
    public Department getDepartmentById(@PathParam("id") int id) {
        Department department = departmentService.getDepartmentById(id);
        department.setEmployees(department.getEmployees());
        return department;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartmentById(@PathParam("id") int id) {
        boolean result = departmentService.deleteDepartmentById(id);
        if (result) {
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
