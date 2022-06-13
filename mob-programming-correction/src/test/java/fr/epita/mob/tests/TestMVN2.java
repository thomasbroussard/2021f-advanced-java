package fr.epita.mob.tests;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.data.ContactCsvDAO;

import java.util.List;

public class TestMVN2 {


    public static void main(String[] args) throws Exception {
        //given
        String filePath = ContactCsvDAO.class.getResource("/17-contacts.csv").toURI().getPath();
        ContactCsvDAO someDao = new ContactCsvDAO(filePath);

        //when

        List<Contact> contactList = someDao.readAll();
        someDao.sort(contactList);


        //then
        if (!(contactList.get(0).getEmail().equals("lpaprocki@hotmail.com"))) {
            throw new Exception("check not passed for TestMVN2");
        }

    }
}
