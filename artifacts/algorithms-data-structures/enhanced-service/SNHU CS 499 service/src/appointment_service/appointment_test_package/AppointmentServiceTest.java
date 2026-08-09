/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */


package appointment_service.appointment_test_package;

import appointment_service.appointment_package.Appointment; // Imports the appointment class
import appointment_service.appointment_package.AppointmentService;// Imports the appointmentService class

import org.junit.jupiter.api.Test; // Import for JUnit testing

import java.time.LocalDateTime; // Import for storing appointment dates and times
import static org.junit.jupiter.api.Assertions.*;
import java.util.List; // Import for testing ordered appointment lists
import java.time.LocalDate; // Import for testing date-range searches
import java.time.LocalTime; // Import for testing time-range searches


public class AppointmentServiceTest {
    private static final long DEFAULT_APPOINTMENT_DURATION_HOURS = 1;

    @Test

    public void testAddAppointmentSuccessfully() { // Test for adding an appointment

        AppointmentService service = new AppointmentService();// Creates a new instance of appointmentService
        LocalDateTime startTime = LocalDateTime.now().plusDays(99);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Appointment appointment = new Appointment(startTime, endTime,
                "The Accountant"); // Creates a new appointment with a start time,
                                             // end time, and description

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
        LocalDateTime startTime = LocalDateTime.now().plusDays(999);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Appointment appointment1 = new Appointment(startTime, endTime,
                "Dr House"); // Creates a new appointment with a start time,
                                       // end time, and description
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
        LocalDateTime startTime = LocalDateTime.now().plusDays(502);
        LocalDateTime endTime = startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Appointment appointment = new Appointment(startTime, endTime,
                "Checkup"); // Creates a new appointment with a start time,
                                       // end time, and description
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
    @Test
    public void testAppointmentsStoredInStartTimeOrder() { //Test for returning appointments in start time order
        AppointmentService service = new AppointmentService();

        LocalDateTime firstStartTime = LocalDateTime.now().plusDays(10);
        LocalDateTime secondStartTime = LocalDateTime.now().plusDays(20);
        LocalDateTime thirdStartTime = LocalDateTime.now().plusDays(30);

        Appointment firstAppointment = new Appointment(
                firstStartTime, firstStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "First appointment");

        Appointment secondAppointment = new Appointment(
                secondStartTime, secondStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "Second appointment");

        Appointment thirdAppointment = new Appointment(
                thirdStartTime, thirdStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "Third appointment");

        // Adds the appointments out of order to test the TreeMap
        service.addAppointment(thirdAppointment);
        service.addAppointment(firstAppointment);
        service.addAppointment(secondAppointment);

        List<Appointment> orderedAppointments = service.getAppointmentsInOrder();

        assertEquals(3, orderedAppointments.size());
        assertEquals(firstAppointment, orderedAppointments.get(0));
        assertEquals(secondAppointment, orderedAppointments.get(1));
        assertEquals(thirdAppointment, orderedAppointments.get(2));
    }

    @Test
    public void testDeletedAppointmentRemovedFromOrderedAppointments() { // Test for verifying
                                                                        // deletion updates the TreeMap
        AppointmentService service = new AppointmentService();

        LocalDateTime startTime = LocalDateTime.now().plusDays(40);
        Appointment appointment = new Appointment(
                startTime, startTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "Delete appointment");

        service.addAppointment(appointment);
        service.deleteAppointment(appointment.getAppointmentID());

        assertTrue(service.getAppointmentsInOrder().isEmpty());
    }

    @Test
    public void testAddAppointmentWithSchedulingConflict() { // Test for adding an overlapping appointment
        AppointmentService service = new AppointmentService();
        LocalDateTime firstStartTime = LocalDateTime.now().plusDays(50);
        LocalDateTime firstEndTime = firstStartTime.plusHours(2);

        Appointment firstAppointment = new Appointment(
                firstStartTime, firstEndTime, "First appointment");

        Appointment conflictingAppointment = new Appointment(
                firstStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), firstEndTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS),
                "Conflicting appointment");

        service.addAppointment(firstAppointment);

        assertThrows(IllegalArgumentException.class, () ->
                // Verifies that an overlapping appointment can't be added
                service.addAppointment(conflictingAppointment)
        );
    }

    @Test
    public void testAppointmentsCanTouchWithoutConflict() { // Tests for appointments that meet at the same time
        AppointmentService service = new AppointmentService();

        LocalDateTime firstStartTime = LocalDateTime.now().plusDays(60);
        LocalDateTime firstEndTime = firstStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS);

        Appointment firstAppointment = new Appointment(
                firstStartTime, firstEndTime, "First appointment");

        Appointment secondAppointment = new Appointment(
                firstEndTime, firstEndTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "Second appointment");

        service.addAppointment(firstAppointment);

        assertDoesNotThrow(() ->
                // The second appointment begins when the first one ends
                service.addAppointment(secondAppointment)

        );

        assertEquals(2, service.getAppointmentsInOrder().size());
    }

    @Test
    public void testUpdateAppointmentSuccessfully() { // Test for updating an appointment
        AppointmentService service = new AppointmentService();

        LocalDateTime originalStartTime = LocalDateTime.now().plusDays(70);
        Appointment appointment = new Appointment(
                originalStartTime, originalStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS),
                "Original appointment");

        service.addAppointment(appointment);
        LocalDateTime updatedStartTime = originalStartTime.plusDays(1);
        LocalDateTime updatedEndTime = updatedStartTime.plusHours(2);

        service.updateAppointment(
                appointment.getAppointmentID(),
                updatedStartTime,
                updatedEndTime,
                "Updated appointment");

        assertEquals(updatedStartTime, appointment.getStartTime());
        assertEquals(updatedEndTime, appointment.getEndTime());
        assertEquals("Updated appointment", appointment.getDescription());

        // Verifies that the updated appointment is still stored in the TreeMap
        assertEquals(appointment, service.getAppointmentsInOrder().get(0));
    }
    @Test
    public void testUpdateAppointmentWithSchedulingConflict() { // Tests for an update that overlaps another appointment
        AppointmentService service = new AppointmentService();

        LocalDateTime firstStartTime = LocalDateTime.now().plusDays(80);
        LocalDateTime secondStartTime = firstStartTime.plusHours(3);

        Appointment firstAppointment = new Appointment(
                firstStartTime, firstStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "First appointment");

        Appointment secondAppointment = new Appointment(
                secondStartTime, secondStartTime.plusHours(DEFAULT_APPOINTMENT_DURATION_HOURS), "Second appointment");

        service.addAppointment(firstAppointment);
        service.addAppointment(secondAppointment);


        assertThrows(IllegalArgumentException.class, () ->
                // Attempts to move the second appointment on top of the first
                service.updateAppointment(
                        secondAppointment.getAppointmentID(),
                        firstStartTime.plusMinutes(30),
                        firstStartTime.plusHours(2),
                        "Conflicting update")
        );

        // Verifies that the failed update didn't change the original appointment
        assertEquals(secondStartTime, secondAppointment.getStartTime());
        assertEquals("Second appointment", secondAppointment.getDescription());
    }

    @Test
    public void testSearchAppointmentsByDateRange() { // Test for finding appointments within a date range
        AppointmentService service = new AppointmentService();

        LocalDate firstDate = LocalDate.now().plusDays(100);
        LocalDate secondDate = LocalDate.now().plusDays(101);
        LocalDate thirdDate = LocalDate.now().plusDays(102);

        Appointment firstAppointment = new Appointment(
                firstDate.atTime(10, 0),
                firstDate.atTime(11, 0),
                "First appointment");

        Appointment secondAppointment = new Appointment(
                secondDate.atTime(10, 0),
                secondDate.atTime(11, 0),
                "Second appointment");

        Appointment thirdAppointment = new Appointment(
                thirdDate.atTime(10, 0),
                thirdDate.atTime(11, 0),
                "Third appointment");

        service.addAppointment(firstAppointment);
        service.addAppointment(secondAppointment);
        service.addAppointment(thirdAppointment);

        List<Appointment> matchingAppointments =
                service.searchAppointmentsByDateRange(firstDate, secondDate);

        assertEquals(2, matchingAppointments.size());
        assertEquals(firstAppointment, matchingAppointments.get(0));
        assertEquals(secondAppointment, matchingAppointments.get(1));


    }

    @Test
    public void testSearchAppointmentsByTimeRange() { // Test for finding appointments within a time range
        AppointmentService service = new AppointmentService();

        LocalDate selectedDate = LocalDate.now().plusDays(111);

        Appointment morningAppointment = new Appointment(
                selectedDate.atTime(9, 0),
                selectedDate.atTime(10, 0),
                "Morning appointment");

        Appointment afternoonAppointment = new Appointment(
                selectedDate.atTime(14, 0),
                selectedDate.atTime(15, 0),
                "Afternoon appointment");

        service.addAppointment(morningAppointment);
        service.addAppointment(afternoonAppointment);

        List<Appointment> matchingAppointments =
                service.searchAppointmentsByTimeRange(
                        selectedDate,
                        LocalTime.of(8, 0),
                        LocalTime.of(12, 0));

        assertEquals(1, matchingAppointments.size());
        assertEquals(morningAppointment, matchingAppointments.get(0));
    }

    @Test
    public void testSearchAppointmentsWithInvalidDateRange() { // Tests for an end date before the start date
        AppointmentService service = new AppointmentService();

        LocalDate startDate = LocalDate.now().plusDays(120);
        LocalDate endDate = startDate.minusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                service.searchAppointmentsByDateRange(startDate, endDate)
        );
    }
    @Test
    public void testSearchAppointmentsWithInvalidTimeRange() { // Test for an end time before the start time
        AppointmentService service = new AppointmentService();
        LocalDate selectedDate = LocalDate.now().plusDays(133);

        assertThrows(IllegalArgumentException.class, () ->
                service.searchAppointmentsByTimeRange(
                        selectedDate,
                        LocalTime.of(15, 0),
                        LocalTime.of(14, 0))
        );

    }




}

