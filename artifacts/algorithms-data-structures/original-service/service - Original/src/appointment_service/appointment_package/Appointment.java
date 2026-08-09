/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */


package appointment_service.appointment_package;

import java.util.Date; // Import for retrieving dates
import java.util.UUID; // Import for generating random ID's


public class Appointment {
    private final String appointmentID; // Made final so it cannot be updated
    private Date appointmentDate; // These are all private so they cannot be
    private String description; // accessed or modified directly from outside the class


    public Appointment(Date appointmentDate, String description) {
        this.appointmentID = generateUniqueID(); // Generates an ID for the appointment

        if (appointmentDate == null || appointmentDate.before(new Date())) { // New date is used to
            // retrieve the current date
            // appointmentDate cannot be null or be before the current date
            // If either is true, an exception is thrown
            throw new IllegalArgumentException("Invalid appointment date");

        }

        if (description == null || description.length() > 50) {
            // description cannot be null or greater than 50 characters
            // If either is true, an exception is thrown
            throw new IllegalArgumentException("Invalid description");

        }

        this.appointmentDate = appointmentDate; // These are used to prevent conflicts
        this.description = description; // between the parameters and the instance variables


    }
    private String generateUniqueID() { // Constructor with an ID generator to
                                        // ensure unique ID's

        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 10); // Ensures ID isn't more than 10 characters
    }


    public String getAppointmentID() { // Getter for the appointmentID
        return appointmentID; // Returns the ID
    }

    public Date getAppointmentDate() { // Getter method for appointmentDate
        return appointmentDate; // Returns the appointment date
    }

    public String getDescription() { // Getter method for the description
        return description; // Returns the description

    }
















}
