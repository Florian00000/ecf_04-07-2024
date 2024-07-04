package com.example.jeejaxrsgestionrh.repository;

import org.hibernate.Session;

import java.util.List;

public abstract class Repository<T> {
    protected Session _session ;

    public Repository(Session _session) {
        this._session = _session;
    }

    public void create(T obj) {
        _session.persist(obj);
    }

    public void update(T obj) {
        _session.merge(obj);
    }

    abstract void delete(T obj) ;

    abstract T findById(int id);

    abstract List<T> findAll();
}
