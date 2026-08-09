/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */


package appointment_service.appointment_test_package;

import appointment_service.appointment_package.Appointment; // Imports the appointment class
import org.junit.jupiter.api.Test; // Import for Junit testing
import java.util.Calendar; // Imports a calendar for date

import java.util.Date; // Import for the date
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;

import java.util.Set; // Used to ensure uniqueness


public class AppointmentTest {

    @Test
    public void testValidAppointmentCreation() { // test for valid appointment creation

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 30); // adds 30 days on top of today
        Date futureDate = calendar.getTime(); // Creates a future date

        Appointment appointment = new Appointment(futureDate, "Ophthalmologist"); // Creates a valid appointment

        assertNotNull(appointment.getAppointmentID()); // Asserts that ID isn't null

        assertNotNull(appointment.getAppointmentDate()); // Asserts date isn't null
        assertEquals(futureDate, appointment.getAppointmentDate()); // Asserts that date matches
        assertNotNull(appointment.getDescription()); // Assert description isn't null
        assertEquals("Ophthalmologist", appointment.getDescription()); // Assert that description matches

    }





    @Test
    public void testAppointmentPastDate() { // Test for an invalid past date
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, -7); // a week ago from today
        Date pastDate = calendar.getTime(); // Creates past date

        assertThrows(IllegalArgumentException.class, () ->

                // Verify that exception is thrown when the date is a past date
                new Appointment(pastDate, "Past appointment")

        );
    }

    @Test

    public void testAppointmentDateIsNull() { // Test for an invalid null date
        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an exception is thrown when the date is null
                new Appointment(null, "No date")
        );


    }

    @Test
    public void testDescriptionIsNull() { // test for an invalid null description
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 35); // 35 days from current date
        Date futureDate = calendar.getTime(); // creates a future date

        assertThrows(IllegalArgumentException.class, () ->
                // Verify an exception is thrown when the description is null
                new Appointment(futureDate, null)
        );
    }


    @Test
    public void testDescriptionTooLong() { // test for an invalid description that's greater than 50 chars
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 90); // 90 days from today
        Date futureDate = calendar.getTime(); // Creates a future date

        String longDescription = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZZZZZZZ"; // Wayyyy toooo long
        assertThrows(IllegalArgumentException.class, () ->
                // Verify an exception is thrown when the description is greater than 50 characters
                new Appointment(futureDate, longDescription)


        );

    }

    @Test
    public void testGeneratedIDsAreUnique() { // Tests ID for uniqueness
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 21); // 21 days from today
        Date futureDate = calendar.getTime(); // Creates a future date

        Set<String> ids = new HashSet<>(); // Stores the generated IDs to check for duplicates


        for (int i = 0; i < 1000; i++) { // Loops a thousand times, creating a new ID every time

            Appointment appointment = new Appointment(futureDate, "Test Appointments");

            String id = appointment.getAppointmentID(); // Gets the generated ID

            assertNotNull(id); // Asserts ID shouldn't be null


            // Verifies that there is no duplicate ID
            assertFalse(ids.contains(id), "Duplicate ID found: " + id);

            ids.add(id); //The ID is added to the set

        }


    }




}

