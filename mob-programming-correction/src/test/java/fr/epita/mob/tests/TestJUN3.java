package fr.epita.mob.tests;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.data.ContactCsvDAO;
import fr.epita.mob.services.exceptions.UnableToLoadContactsException;
import org.junit.jupiter.api.*;

import java.net.URISyntaxException;
import java.util.List;

public class TestJUN3 {


    private static String filePath;

    private ContactCsvDAO contactCsvDAO;


    @BeforeAll
    public static void beforeAllTest() throws URISyntaxException {
        filePath = ContactCsvDAO.class
                .getResource("/17-contacts.csv")
                .toURI()
                .getPath();

    }

    @BeforeEach
    public void beforeEachTest() {
        //given
        this.contactCsvDAO = new ContactCsvDAO(filePath);
    }

    @Test
    public void deserializationTest() throws Exception {
        //given the file and the dao
        //when
        List<Contact> contactList = this.contactCsvDAO.readAll();
        contactCsvDAO.sort(contactList);

        Contact shouldBeFoundContact =
                new Contact("Lenna", "Paprocki", "907-385-4412", "lpaprocki@hotmail.com");

        //then
        Assertions.assertEquals(contactList.get(0), shouldBeFoundContact);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each");
    }


    @AfterAll
    public static void afterAll() {
        System.out.println("after all");
    }


}
