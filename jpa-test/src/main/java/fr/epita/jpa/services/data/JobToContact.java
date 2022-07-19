package fr.epita.jpa.services.data;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.datamodel.Job;

import javax.persistence.ManyToOne;

public class JobToContact {


    @ManyToOne
    Job job;

    @ManyToOne
    Contact contact;
}
