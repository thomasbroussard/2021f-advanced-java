package fr.epita.jpa.tests;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.services.data.JPADAO;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

public class TestGenericJPA_AnonymousInstanciation {



    @Test
    public void testSessionFactory(){
        //given
        Contact contact = new Contact();
        contact.setAddress("test address");
        contact.setName("test");
        contact.setId(1L);

        //when


    }
}
