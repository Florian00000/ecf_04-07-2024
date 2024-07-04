package com.example.jeejaxrsgestionrh.service;

import com.example.jeejaxrsgestionrh.entitie.Position;
import com.example.jeejaxrsgestionrh.repository.PositionRepository;
import com.example.jeejaxrsgestionrh.util.HibernateSession;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@ApplicationScoped
public class PositionService {

    private PositionRepository positionRepository;
    private SessionFactory sessionFactory;
    private Session session;

    public PositionService() {
        this.sessionFactory = HibernateSession.getSessionFactory();
    }

    public List<Position> findAll() {
        session = sessionFactory.openSession();
        positionRepository = new PositionRepository(session);
        List<Position> positions = positionRepository.findAll();
        session.close();
        return positions;
    }

    public Position findById(int id) {
        session = sessionFactory.openSession();
        positionRepository = new PositionRepository(session);
        Position position = positionRepository.findById(id);
        session.close();
        return position;
    }

    public Position createPosition(String name) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        positionRepository = new PositionRepository(session);
        Position position = Position.builder().name(name).build();
        positionRepository.create(position);
        session.getTransaction().commit();
        session.close();
        return position;
    }

    public boolean deletePosition(int id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        positionRepository = new PositionRepository(session);
        Position position = positionRepository.findById(id);
        if (position != null) {
            positionRepository.delete(position);
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
