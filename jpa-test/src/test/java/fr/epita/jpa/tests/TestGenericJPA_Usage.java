package fr.epita.jpa.tests;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.datamodel.Job;
import fr.epita.jpa.services.data.JPADAO;
import fr.epita.jpa.tests.BaseTestConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestConfig.class)
public class TestGenericJPA_Usage {


    @Inject
    JPADAO<Contact> contactJPADAO;

    @Test
    public void testSessionFactory(){
        //given
        Contact contact = new Contact();
        contact.setAddress("test address");
        contact.setName("test");
        contact.setId(1L);

        //when
        contactJPADAO.create(new Contact());

    }


}
