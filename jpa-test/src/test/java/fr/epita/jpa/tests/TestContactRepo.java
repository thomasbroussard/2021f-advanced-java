package fr.epita.jpa.tests;


import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.datamodel.Job;
import fr.epita.jpa.services.data.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestConfig.class)
public class TestContactRepo {

    @Inject
    ContactRepository repo;


    @Test
    public void test(){
        Contact contact = new Contact();
        contact.setName("test");

        Job job = new Job();
        job.setJobTitle("software developer");
        repo.saveContactAndJob(contact, job);




    }


}
