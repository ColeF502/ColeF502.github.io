/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */



package contact_service.contact_package;
import java.util.UUID; // Import for generating random ID's
public class Contact {

    private static final int CONTACT_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int PHONE_LENGTH = 10;
    private static final int MAX_ADDRESS_LENGTH = 30;

    private final String contactID; // Declared contactID final so it cannot be updated

    private String firstName; // These are all private so they cannot be accessed or-
    private String lastName;  // modified directly from outside the class
    private String phone;
    private String address;


    public Contact(String firstName, String lastName, String phone, String address) {
        this.contactID = generateUniqueID(); // Generates a unique ID



        if (firstName == null || firstName.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Invalid first name");
        // ensures firstName is not null or is not longer than 10 characters, if either is true, an exception is thrown

        if (lastName == null || lastName.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Invalid last name");
        // ensures lastName is not null or is not longer than 10 characters, if either is true, an exception is thrown

        if (phone == null || phone.length() != PHONE_LENGTH) throw new IllegalArgumentException("Invalid phone number");
        // ensures that phone is not null or is not equal to 10 digits, if either are true, an exception is thrown

        if (address == null || address.length() > MAX_ADDRESS_LENGTH) throw new IllegalArgumentException("Invalid address");
        // ensures that address is not null or is not greater than 30 characters, if either is true, an exception is thrown




        this.firstName = firstName; // These are used to prevent conflicts between-
        this.lastName = lastName;// the parameters and the instance variabels
        this.phone = phone;
        this.address = address;


    }
    /*
    Feedback for the module 3 milestone wanted a generator to ensure uniqueness,
    I have added a generator for IDs to this assignment for the project
     */
    private static String generateUniqueID() { // Generator for unique ID's
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, CONTACT_ID_LENGTH);
    }

    public String getContactID() { // Getter method for contactID
        return contactID;
    }


    public String getFirstName() { // Getter for firstName
        return firstName;
    }

    public void setFirstName(String firstName) { // setter for firstName
        if (firstName == null || firstName.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Invalid first name");

        this.firstName = firstName; // As long as the input is not null and is less
                                    // than 11 characters, the input is assigned to firstName
    }
    public String getLastName() { // Getter for lastName
        return lastName;
    }

    public void setLastName(String lastName) { // Setter for lastName
        if (lastName == null || lastName.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("Invalid last name");
        this.lastName = lastName; // As long as the input is not null and is less than 11
                                    // characters, the input is assigned to lastName


    }

    public String getPhone() { //Getter method for phone
        return phone;
    }

    public void setPhone(String phone) { // Setter for phone
        if (phone == null || phone.length() != PHONE_LENGTH || !phone.matches("\\d+")) throw new IllegalArgumentException("Invalid phone number");
        this.phone = phone; // AS long as the input is not null and the length is equal
                            // to 10 digits, the input is assigned to phone
    }

    public String getAddress() { // Getter for address
        return address;
    }

    public void setAddress(String address) { // Setter for address

        if (address == null || address.length() > MAX_ADDRESS_LENGTH) throw new IllegalArgumentException("Invalid address");
        this.address = address; // As long as input is not null and is not
                                // more than 30 characters, the input is assigned to address


    }




}
