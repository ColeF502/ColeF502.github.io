/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package contact_service.contact_test_package;

import org.junit.jupiter.api.BeforeEach; // Import for @BeforeEach to use the set up for the tests

import org.junit.jupiter.api.Test; //IMport for the JUnit testing
import static org.junit.jupiter.api.Assertions.*;

import contact_service.contact_package.Contact; //Imports the Contact class
import contact_service.contact_package.ContactService;// Import the Contact Service class

public class ContactServiceTest {


    private ContactService service;

    private Contact contact;


    @BeforeEach
    public void setUp() { // setUp needed for each of the tests, creates the initial contact

        service = new ContactService();
        contact = new Contact("Allison", "Mickey", "1234567890", "176 Paradise Rd");
        service.addContact(contact);

    }

    @Test
    public void testAddContactSuccessfully() { // Test to successfully add a contact
        Contact newContact = new Contact("Cullen", "Jameson", "0987654321", "1428 Elm St");
        service.addContact(newContact);
    }



    @Test
    public void testAddDuplicateContactID() { // This matches the initial contact, so it should fail, since it's a duplicate

        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact));
    }

    @Test
    public void testDeleteContactSuccessfully() { // Gets the intitial contact's ID, so it should match, then delete the contact
        service.deleteContact(contact.getContactID());

        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(contact.getContactID()));

    }



    @Test
    public void testDeleteNonExistentContact() { // Fails because it's trying to delete a contact that doesn't exist
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("0"));
    }

    @Test

    public void testUpdateFirstName() { // tests for firstName update
        service.updateFirstName(contact.getContactID(), "Mikayla");
        assertEquals("Mikayla", contact.getFirstName());
    }

    @Test
    public void testUpdateLastName() { // Tests for lastName update

        service.updateLastName(contact.getContactID(), "Fredericks");

        assertEquals("Fredericks", contact.getLastName());
    }



    @Test
    public void testUpdatePhoneNumber() { // Tests for phone number update
        service.updatePhoneNumber(contact.getContactID(), "3155555555");
        assertEquals("3155555555", contact.getPhone());


    }


    @Test
    public void testUpdateAddress() { // test for address update
        service.updateAddress(contact.getContactID(), "1428 Elm St");
        assertEquals("1428 Elm St", contact.getAddress());

    }








}

