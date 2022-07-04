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
        Session session = getSession();
        session.persist(something);
    }

    public void delete(T something){
        Session session = getSession();
        session.delete(something);
    }


    public void update(T something){
        Session session = getSession();
        session.update(something);
    }

    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.factory.getCurrentSession();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.factory.openSession();
        }
    }

    public List<T> search(T criteria){
        Session session = getSession();
        Query<T> query = getQuery(criteria, session);
        return query.list();


    }

    public abstract Query<T> getQuery(T criteria, Session session);

}
