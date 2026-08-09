/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */


package appointment_service.appointment_package;

import java.util.HashMap; // These are used to store key-value pairs
import java.util.Map;


public class AppointmentService {
    private final Map<String, Appointment> appointments; // Made final so the map cannot be changed

    public AppointmentService() {
        appointments = new HashMap<>(); // Creates a hashmap for storage
    }


    public void addAppointment(Appointment appointment) { // Way to add an appointment
        if (appointment == null) { // Ensures appointment isn't null
                                    // If it is, an exception is thrown
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        String id = appointment.getAppointmentID(); // Gets the generated ID
                                                    // from the Appointment class

        if (appointments.containsKey(id)) { // Checks to the appointments map to see
                                            //if that ID already exits
                                        // If the ID does exist, an exception is thrown
            throw new IllegalArgumentException("Duplicate appointment ID");
        }


        appointments.put(id, appointment); // If neither are true, the appointment is added to the map

    }



    public void deleteAppointment(String appointmentID) { // Way to delete an appointment
        if (appointmentID == null || !appointments.containsKey(appointmentID)) {
            // If the appointment ID is null or the ID
            // cannot be found and exception is thrown
            throw new IllegalArgumentException("Appointment ID does not exist");
        }

        appointments.remove(appointmentID); // Deletes the appointment if it's found
    }







}

