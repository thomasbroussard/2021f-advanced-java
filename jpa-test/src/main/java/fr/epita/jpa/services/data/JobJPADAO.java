package fr.epita.jpa.services.data;

import fr.epita.jpa.datamodel.Contact;
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
       return null;
    }
}
