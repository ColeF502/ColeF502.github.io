/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */


package appointment_service.appointment_test_package;

import appointment_service.appointment_package.Appointment; // Imports the appointment class
import appointment_service.appointment_package.AppointmentService;// Imports the appointmentService class

import org.junit.jupiter.api.Test; // Import for JUnit testing
import java.util.Calendar; // Imports a calendar for date
import java.util.Date; // Import for the date
import static org.junit.jupiter.api.Assertions.*;


public class AppointmentServiceTest {

    @Test

    public void testAddAppointmentSuccessfully() { // Test for adding an appointment

        AppointmentService service = new AppointmentService();// Creates a new instance of appointmentService
        Calendar calendar = Calendar.getInstance(); // Creates a calendar object
        calendar.add(Calendar.DATE, 99); //99 days from today
        Date futureDate = calendar.getTime(); // Creates the future date

        Appointment appointment = new Appointment(futureDate, "The Accountant");// Creates a new appointment
                                                                                  // object with the date and description

        assertDoesNotThrow(() -> //Verifies that no exception is thrown when adding the new appointment

                service.addAppointment(appointment) // Calls the addAppointment method and if fields
        );									   // are valid, it adds the new appointment

        assertThrows(IllegalArgumentException.class, () -> // Verifies that an exception is thrown if
                                                 // the same appointment is trying to be added again, which shouldn't
                                                   // be allowed because then they'd be duplicates and invalid

                service.addAppointment(appointment) // Attempting add the same appointment again

        );

    }


    @Test

    // This test is much like the previous one, except this one is explicitly for testing duplicates
    public void testAddDuplicateAppointment() {// Test for adding a duplicate appointment
        AppointmentService service = new AppointmentService(); // Creates a new instance of appointmentService
        Calendar calendar = Calendar.getInstance(); //Creates a calendar object

        calendar.add(Calendar.DATE, 999); // 999 days from the current date
        Date futureDate = calendar.getTime(); // Creates the future date

        Appointment appointment1 = new Appointment(futureDate, "Dr House");//Creates a new appointment
                                                                                // object with the date and description
        service.addAppointment(appointment1); // Calls the addAppointment method and if fields
                                                // are valid, the new appointment is added

        assertThrows(IllegalArgumentException.class, () ->
                // Verify that an exception is thrown when trying to add
                // the same appointment again, verifying that duplicates aren't allowed
                service.addAppointment(appointment1) // Tries to add the same appointment again
        );

    }
    @Test
    public void testDeleteAppointmentSuccessfully() { // Test for deleting an appointment


        AppointmentService service = new AppointmentService(); //
        Calendar calendar = Calendar.getInstance(); // Creates a calendar object
        calendar.add(Calendar.DATE, 502); // 502 days in the future from today
        Date futureDate = calendar.getTime(); // creates the future date

        Appointment appointment = new Appointment(futureDate, "Checkup"); // Creates a new appointment object
                                                                                  // with the future date and description
        service.addAppointment(appointment); // Calls the addAppointment method and if all fields
                                               // are valid, the appointment is added
        service.deleteAppointment(appointment.getAppointmentID()); // Gets the appointment ID and and deletes it

        assertThrows(IllegalArgumentException.class, () ->
                // Verify that an exception is thrown when trying to delete the appointment that was just deleted
                service.deleteAppointment(appointment.getAppointmentID())


        );
    }


    @Test
    public void testDeleteAppointmentWithInvalidID() { // Test for trying to delete
                                                    // an appointment with an invalid ID
        AppointmentService service = new AppointmentService(); // Creates a new instance of appointmentService

        assertThrows(IllegalArgumentException.class, () ->
                   // Verify that an exception is thrown when trying to delete an appointment using a nonexistent ID
               service.deleteAppointment("An ID that most certainly does not exist") // Uses an ID that doesn't exist,
                                                                                        // making the test fail purposely
        );
    }

    @Test

    public void testDeleteAppointmentWithNullID() { // Test for trying to delete an appointment
                                                        // with a null ID

        AppointmentService service = new AppointmentService(); // Creates a new instance of appointmentService


        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an exception is thrown when trying to delete an appointment using a null ID
                service.deleteAppointment(null) // Attempts to delete the appointment
        );



    }




}

