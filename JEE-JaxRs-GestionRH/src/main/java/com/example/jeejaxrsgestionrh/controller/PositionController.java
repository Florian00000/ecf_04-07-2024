package com.example.jeejaxrsgestionrh.controller;

import com.example.jeejaxrsgestionrh.entitie.Position;
import com.example.jeejaxrsgestionrh.service.PositionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/position")
@Produces(MediaType.APPLICATION_JSON)
public class PositionController {

    private final PositionService positionService;

    @Inject
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GET
    public List<Position> getAllPositions() {
        List<Position> positions = positionService.findAll();
        return positions.stream().peek(position -> {
            position.setEmployees(position.getEmployees());
        }).collect(Collectors.toList());
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Position addPosition(Position position) {
        return positionService.createPosition(position.getName());
    }

    @GET
    @Path("/{id}")
    public Position getPositionById(@PathParam("id") int id) {
        Position position = positionService.findById(id);
        position.setEmployees(position.getEmployees());
        return position;
    }

    @DELETE
    @Path("/{id}")
    public Response deletePositionById(@PathParam("id") int id) {
        boolean success = positionService.deletePosition(id);
        if (success) {
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
