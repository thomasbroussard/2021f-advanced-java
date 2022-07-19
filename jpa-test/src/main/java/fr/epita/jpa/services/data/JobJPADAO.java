package fr.epita.jpa.services.data;

import fr.epita.jpa.datamodel.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class JobJPADAO extends JPADAO<Job>{


    public JobJPADAO(SessionFactory sf) {
        super(sf);
    }

    @Override
    public Query<Job> getQuery(Job criteria, Session session) {
        //TODO implement
        Query<Job> query = session.createQuery("from Job where id = :id");
        query.setParameter("id", criteria.getId());
        return query;
    }
}
