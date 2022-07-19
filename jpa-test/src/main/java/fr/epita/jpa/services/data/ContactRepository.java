package fr.epita.jpa.services.data;

import fr.epita.jpa.datamodel.Contact;
import fr.epita.jpa.datamodel.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;

public class ContactRepository {


    private final SessionFactory sessionFactory;
    private final ContactJPADAO contactDAO;
    private final JobJPADAO jobDAO;

    public ContactRepository(SessionFactory sf,
                             ContactJPADAO contactDAO,
                             JobJPADAO jobDAO) {
        this.sessionFactory = sf;
        this.contactDAO = contactDAO;
        this.jobDAO = jobDAO;
    }

    @Transactional
    public void saveContactAndJob(Contact contact, Job job) {

        List<Job> searchResult = this.jobDAO.search(job);
        Job foundJob = null;
        if (searchResult == null || searchResult.isEmpty()) {
            this.jobDAO.create(job);
            foundJob = job;
        } else {
            foundJob = searchResult.get(0);
        }
        if (foundJob.getId() == null) {
            //error case, we should not have a null id for the job
            return;
        }
        contact.setJob(foundJob);
        this.contactDAO.create(contact);
    }




    //todo : remove duplicate
    private Session getSession() {
        Session currentSession = null;
        try {
            currentSession = this.sessionFactory.getCurrentSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (currentSession != null && currentSession.isOpen()) {
            return currentSession;
        } else {
            return this.sessionFactory.openSession();
        }
    }


}
