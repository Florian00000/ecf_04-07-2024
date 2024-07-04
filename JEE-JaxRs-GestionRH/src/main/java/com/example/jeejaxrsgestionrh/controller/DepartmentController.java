package com.example.jeejaxrsgestionrh.controller;

import com.example.jeejaxrsgestionrh.entitie.Department;
import com.example.jeejaxrsgestionrh.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
        return departmentService.getAllDepartments();
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
        return departmentService.getDepartmentById(id);
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
