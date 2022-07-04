package fr.epita.jpa.services.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class JPADAO<T> {

    private final SessionFactory factory;


    public JPADAO(SessionFactory sf){
        this.factory =sf;
    }

    public void create(T something){
        Session session = this.factory.openSession();
        session.persist(something);
    }

    public List<T> search(T criteria){
        Session session = this.factory.openSession();
        Query<T> query = getQuery(criteria, session);
        return query.list();


    }

    public abstract Query<T> getQuery(T criteria, Session session);

}
