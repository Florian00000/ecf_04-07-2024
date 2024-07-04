package com.example.jeejaxrsgestionrh.repository;

import com.example.jeejaxrsgestionrh.entitie.Position;
import org.hibernate.Session;

import java.util.List;

public class PositionRepository extends Repository<Position> {

    public PositionRepository(Session _session) {
        super(_session);
    }

    @Override
    public Position findById(int id) {
        try {
            return (Position) _session.get(Position.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Position> findAll() {
        return _session.createQuery("from Position", Position.class).list();
    }
}
