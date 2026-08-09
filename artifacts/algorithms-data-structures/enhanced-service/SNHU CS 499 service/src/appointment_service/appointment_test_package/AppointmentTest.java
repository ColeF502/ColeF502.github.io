/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */


package appointment_service.appointment_test_package;

import appointment_service.appointment_package.Appointment; // Imports the appointment class
import org.junit.jupiter.api.Test; // Import for Junit testing

import java.time.LocalDateTime; // Import for storing appointment dates and times

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;

import java.util.Set; // Used to ensure uniqueness


public class AppointmentTest {

    private static final long DEFAULT_APPOINTMENT_DURATION_HOURS = 1;

    @Test
    public void testValidAppointmentCreation() { // test for valid appointment creation

        LocalDateTime startTime = LocalDateTime.now().plusDays(30);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Appointment appointment = new Appointment(startTime, endTime,
                "Ophthalmologist"); // Creates a valid appointment

        assertNotNull(appointment.getAppointmentID()); // Asserts that ID isn't null

        assertNotNull(appointment.getStartTime()); // Asserts start time isn't null
        assertEquals(startTime, appointment.getStartTime()); // Asserts that start time matches
        assertNotNull(appointment.getEndTime()); // Asserts end time isn't null
        assertEquals(endTime, appointment.getEndTime()); // Asserts that end time matches

        assertNotNull(appointment.getDescription()); // Assert description isn't null
        assertEquals("Ophthalmologist", appointment.getDescription()); // Assert that description matches

    }





    @Test
    public void testAppointmentStartTimeInPast() { // Test for an invalid past date
        LocalDateTime startTime = LocalDateTime.now().minusDays(7);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        assertThrows(IllegalArgumentException.class, () ->

                // Verify that an exception is thrown when the start time is in the past
                new Appointment(startTime, endTime, "Past appointment")

        );
    }
    @Test
    public void testAppointmentStartTimeIsNull() {
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an exception is thrown when the start time is null
                new Appointment(null, endTime, "No start time")
        );
    }

    @Test
    public void testAppointmentEndTimeIsNull() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an exception is thrown when the end time is null
                new Appointment(startTime, null, "No end time")
        );
    }

    @Test
    public void testAppointmentEndTimeBeforeStartTime() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(2);
        LocalDateTime endTime = startTime.minusHours(1);
        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an appointment can't end before it starts
                new Appointment(startTime, endTime, "Invalid times")
        );

    }

    @Test
    public void testAppointmentEndTimeMatchesStartTime() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(2);

        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an appointment must have some length
                new Appointment(startTime, startTime, "Invalid times")
        );
    }
    @Test
    public void testDescriptionIsNull() { // Test for an invalid null description
        LocalDateTime startTime = LocalDateTime.now().plusDays(35);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        assertThrows(IllegalArgumentException.class, () ->
                // Verify an exception is thrown when the description is null
                new Appointment(startTime, endTime, null)
        );
    }


    @Test
    public void testDescriptionTooLong() { // Test for an invalid description that's greater than 50 chars
        LocalDateTime startTime = LocalDateTime.now().plusDays(90);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        String longDescription = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZZZZZZZ"; // Wayyyy toooo long
        assertThrows(IllegalArgumentException.class, () ->
                // Verify an exception is thrown when the description is greater than 50 characters
                new Appointment(startTime, endTime, longDescription)


        );

    }

    @Test
    public void testGeneratedIDsAreUnique() { // Tests ID for uniqueness
        LocalDateTime startTime = LocalDateTime.now().plusDays(21);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Set<String> ids = new HashSet<>(); // Stores the generated IDs to check for duplicates


        for (int i = 0; i < 1000; i++) { // Loops a thousand times, creating a new ID every time

            Appointment appointment =
                    new Appointment(startTime, endTime, "Test Appointments");

            String id = appointment.getAppointmentID(); // Gets the generated ID

            assertNotNull(id); // Asserts ID shouldn't be null


            // Verifies that there is no duplicate ID
            assertFalse(ids.contains(id), "Duplicate ID found: " + id);

            ids.add(id); //The ID is added to the set

        }


    }




}

