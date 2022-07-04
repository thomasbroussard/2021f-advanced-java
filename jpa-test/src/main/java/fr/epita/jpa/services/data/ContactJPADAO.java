package fr.epita.jpa.services.data;


import fr.epita.jpa.datamodel.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ContactJPADAO {

    SessionFactory factory;


    public ContactJPADAO(SessionFactory factory){
        this.factory = factory;
    }


    public void create(Contact contact){
        Session session = this.factory.openSession();
        session.persist(contact);
    }

    public List<Contact> search(Contact criteria){
        Session session = this.factory.openSession();
        Query<Contact> query = session.createQuery(
                "from Contact c " +
                        "where (:name is null or :name = c.name) " +
                        "and (:address is null or :address = c.address)"
        );

        query.setParameter("name", criteria.getName());
        query.setParameter("address", criteria.getAddress());

        return query.list();


    }




}
