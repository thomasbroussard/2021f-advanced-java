package fr.epita.mob.tests;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.data.ContactCsvDAO;

import java.util.List;

public class TestMVN2 {


    public static void main(String[] args) throws Exception {
        //given
        ContactCsvDAO someDao = new ContactCsvDAO();


        //when
        List<Contact> contactList = someDao.readAll();
        someDao.sort(contactList);


        //then
        if (!(contactList.get(0).getEmail().equals("lpaprocki@hotmail.com"))){
            throw new Exception("check not passed for TestMVN2");
        }

    }
}
