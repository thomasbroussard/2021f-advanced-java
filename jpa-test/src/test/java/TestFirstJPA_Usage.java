import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.tests.BaseTestConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestConfig.class)
public class TestFirstJPA_Usage {

    @Inject
    SessionFactory sf;

    @Test
    public void testSessionFactory(){
        //given
        Contact contact = new Contact();
        contact.setAddress("test address");
        contact.setName("test");
        contact.setId(1L);

        //when
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(contact);
        transaction.commit();

        //then
        Contact foundContact = session.get(Contact.class, 1L);
        Assertions.assertEquals(contact.getName(), foundContact.getName());

        session.createQuery("from Contact",Contact.class);
    }


}
