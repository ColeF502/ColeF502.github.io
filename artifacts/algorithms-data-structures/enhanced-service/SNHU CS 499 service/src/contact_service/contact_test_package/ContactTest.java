/*
Cole Fredericks
7/24/26
SNHU CS 499
4-2 Milestone Three
 */

package contact_service.contact_test_package;

import org.junit.jupiter.api.Test; // Import for JUnit tests
import static org.junit.jupiter.api.Assertions.*;
import contact_service.contact_package.Contact; // IMports the Contact class


public class ContactTest {

    private static final String VALID_FIRST_NAME = "Allison";
    private static final String VALID_LAST_NAME = "Mickey";
    private static final String VALID_PHONE = "1234567890";
    private static final String VALID_ADDRESS = "176 Paradise Rd";

    @Test
    public void testValidContactCreation() { // Tests for valid contact creation
        Contact contact = new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);

        assertNotNull(contact.getContactID());
        assertEquals(10, contact.getContactID().length()); // Assert ID is 10 characters
        assertEquals(VALID_FIRST_NAME, contact.getFirstName()); // Assert first name matches
        assertEquals(VALID_LAST_NAME, contact.getLastName()); // Assert last name matches
        assertEquals(VALID_PHONE, contact.getPhone()); // Assert phone matches
        assertEquals(VALID_ADDRESS, contact.getAddress());// Assert address matches

    }




    @Test
    public void testInvalidFirstName() { // Test that firstName cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> { // Test for null
            new Contact(null, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact("Allisonnnnnnnn", VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }

    @Test
    public void testInvalidLastName() { //Test that lastName cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> { // Test for null
            new Contact(VALID_FIRST_NAME, null, VALID_PHONE, "123 Main St");
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact(VALID_FIRST_NAME, "Mickeyyyyyyyyyyy", VALID_PHONE, VALID_ADDRESS);
        });
    }


    @Test
    public void testInvalidPhoneNumber() { // Tests that phone number is 10 digits
        assertThrows(IllegalArgumentException.class, () -> { // test for null
            new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, null, VALID_ADDRESS);
        });

        assertThrows(IllegalArgumentException.class, () -> { // Test for length
            new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, "5555555", VALID_ADDRESS);
        });
    }
    @Test
    public void testInvalidAddress() { // Test that address cannot be null or too long
        assertThrows(IllegalArgumentException.class, () -> {// Test for null
            new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, null);
        });

        assertThrows(IllegalArgumentException.class, () -> { // test for length
            new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, "An address that is clearly wayyyyyyyyy too longgggggggggggggggggggg");
        });
    }



    @Test
    public void testSettersWorkCorrectly() { // Test for the setters
        Contact contact = new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);

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
        Contact contact = new Contact(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);


        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("Anesaaaaaaaaaaaaaaaaa")); // Too many characters
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("Harrisssssssssssssss")); // too many characters
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("13158675309")); // one too long
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null)); // null is invalid

    }

}

