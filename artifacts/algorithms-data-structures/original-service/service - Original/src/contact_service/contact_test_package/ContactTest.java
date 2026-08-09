/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package contact_service.contact_test_package;

import org.junit.jupiter.api.Test; // Import for JUnit tests
import static org.junit.jupiter.api.Assertions.*;
import contact_service.contact_package.Contact; // IMports the Contact class


public class ContactTest {

    @Test
    public void testValidContactCreation() { // Tests for valid contact creation
        Contact contact = new Contact("Allison", "Mickey", "1234567890", "176 Paradise Rd");

        assertNotNull(contact.getContactID());
        assertEquals(10, contact.getContactID().length()); // Assert ID is 10 characters
        assertEquals("Allison", contact.getFirstName()); // Asserts first name matches
        assertEquals("Mickey", contact.getLastName()); // Assert last name macthes
        assertEquals("1234567890", contact.getPhone()); // Assert phone number matches
        assertEquals("176 Paradise Rd", contact.getAddress()); // Asserts the address matches

    }




    @Test
    public void testInvalidFirstName() { // Test that firstName cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> { // Test for null
            new Contact(null, "Mickey", "1234567890", "176 Paradise Rd");
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact("Allisonnnnnnnn", "Mickey", "1234567890", "176 Paradise Rd");
        });
    }

    @Test
    public void testInvalidLastName() { //Test that lastName cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> { // Test for null
            new Contact("Allison", null, "1234567890", "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact("Allison", "Mickeyyyyyyyyyyy", "1234567890", "176 Paradise Rd");
        });
    }


    @Test
    public void testInvalidPhoneNumber() { // Tests that phone number is 10 digits
        assertThrows(IllegalArgumentException.class, () -> { // test for null
            new Contact("Allison", "Mickey", null, "176 Paradise Rd");
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact("Allison", "Mickey", "5555555", "176 Paradise Rd");
        });
    }
    @Test
    public void testInvalidAddress() { // Test that address cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> {// Test for null
            new Contact("Allison", "Mickey", "1234567890", null);
        });

        assertThrows(IllegalArgumentException.class, () -> { // test for length
            new Contact("Allison", "Mickey", "1234567890", "An address that is clearly wayyyyyyyyy too longgggggggggggggggggggg");
        });
    }



    @Test
    public void testSettersWorkCorrectly() { // Test for the setters
        Contact contact = new Contact("Allison", "Mickey", "1234567890", "176 Paradise Rd");

        contact.setFirstName("Mikayla"); // Set firstName
        assertEquals("Mikayla", contact.getFirstName());

        contact.setLastName("Fredericks"); // Set lastName
        assertEquals("Fredericks", contact.getLastName());

        contact.setPhone("3158675309"); // Set phone number
        assertEquals("3158675309", contact.getPhone());

        contact.setAddress("1428 Elm St"); // Set address
        assertEquals("1428 Elm St", contact.getAddress());
    }

    @Test
    public void testSettersWithInvalidInputs() { // Test for invalid inputs
        Contact contact = new Contact("Allison", "Mickey", "1234567890", "176n Paradise Rd");


        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("Anesaaaaaaaaaaaaaaaaa")); // Too many characters
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("Harrisssssssssssssss")); // too many characters
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("13158675309")); // one too long
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null)); // null is invalid

    }

}

