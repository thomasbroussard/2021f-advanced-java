package fr.epita.jpa.services.data;


import fr.epita.jpa.datamodel.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ContactJPADAO extends JPADAO<Contact>{


    public ContactJPADAO(SessionFactory sf) {
        super(sf);
    }


    public Query<Contact> getQuery(Contact criteria, Session session) {
        Query<Contact> query = session.createQuery(
                "from Contact c " +
                        "where (:name is null or :name = c.name) " +
                        "and (:address is null or :address = c.address)"
        );

        query.setParameter("name", criteria.getName());
        query.setParameter("address", criteria.getAddress());
        return query;
    }
}
