package fr.epita.mob.services.data;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.exceptions.UnableToLoadContactsException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactCsvDAO {

    private final String location;

    public ContactCsvDAO(String location){
        this.location = location;
    }

    public List<Contact> readAll() throws UnableToLoadContactsException {
        File file = new File(this.location);
        List<String> rawStringList = null;
        try {
            rawStringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("we had a technical problem, unable to load contact file from location: " + file.toPath());
            throw new UnableToLoadContactsException(e);
        }

        //remove the header line
        rawStringList.remove(0);


        List<Contact> contactList = new ArrayList<>();
        for (String row : rawStringList){
            String[] parts = row.split(",");
            String firstName = parts[0];
            String lastName = parts[1];
            String email = parts[10];
            String phoneNumber = parts[9];
            String state = parts[6];

            //  TODO complete the mapping to add more fields to our contact (only 4 out of 11 here)
            Contact contact = new Contact(firstName,lastName,phoneNumber,email);
            contact.setState(state);
            contactList.add(contact);


        }

        return contactList;

    }

    public void sort(List<Contact> contactList) {
//        Collections.sort(contactList, new Comparator<Contact>() {
//            @Override
//            public int compare(Contact o1, Contact o2) {
//                return o1.getState().compareTo(o2.getState());
//            }
//        });

        Collections.sort(contactList, (contact1, contact2) -> {
           return contact1.getState().compareTo(contact2.getState());
        });

//        Collections.sort(contactList, Comparator.comparing(Contact::getState, (state1, state2) -> state2.compareTo(state1)));
    }
}
