/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */
package contact_service.contact_package;

import java.util.Map; // These are used to store key-value pairs
import java.util.HashMap;
public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>(); // Made final so the map cannot be changed
                                                                    // This creates a hashmap for storage and
                                                                        // contactID is the key


    public void addContact(Contact contact) { // Way to add a new contactID
        if (contacts.containsKey(contact.getContactID())) { // Checks the contacts map to
            // see if that contactID already exists
            throw new IllegalArgumentException("Contact ID already exists.");// If that contactId already
            // exists, an exception is thrown
        }
        contacts.put(contact.getContactID(), contact); // If that contactID does not exist,
        // a new contact is added to the map
    }

    public void deleteContact(String contactID) { // Way to delete a contactID
        if (!contacts.containsKey(contactID)) { // Checks the contacts map to make sure that contactID exists
            throw new IllegalArgumentException("Contact ID not found."); // If the contactID does not exist,
                                                                            // an exception is thrown
        }
        contacts.remove(contactID); // If the contactID is found, it is then deleted from the map
    }

    public void updateFirstName(String contactID, String newFirstName) { // Way to update the firstName of a contact

        Contact contact = getContact(contactID); // Gets the contact using the contactID
        contact.setFirstName(newFirstName); // Sets the new firstName of the contact
    }



    public void updateLastName(String contactID, String newLastName) { // Way to update the lastName of a contact
        Contact contact = getContact(contactID); // Gets the contact using the contactID
        contact.setLastName(newLastName); //Sets the new lastName


    }

    public void updatePhoneNumber(String contactID, String newPhone) { // Way to update the phone number of a contact
        Contact contact = getContact(contactID); // Gets the contact using the contactID

        contact.setPhone(newPhone); // Sets the new phone number of the contact

    }
    public void updateAddress(String contactID, String newAddress) { //Way to update the address of a contact
        Contact contact = getContact(contactID); // Gets the contact using the contactID
        contact.setAddress(newAddress);  // Set the new address of the contact

    }



    private Contact getContact(String contactID) { // Way to retrieve a contact
        Contact contact = contacts.get(contactID); // Looks for the contact in the contacts map

        if (contact == null) { // If the contact is not found, an exception is thrown
            throw new IllegalArgumentException("Contact not found.");
        }

        return contact; // If the contact is found, it's returned


    }










}

