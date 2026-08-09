/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */


package appointment_service.appointment_package;

import java.time.LocalDateTime; // Import for storing appointment dates and times
import java.util.UUID; // Import for generating random ID's


public class Appointment {
    private static final int APPOINTMENT_ID_LENGTH = 10;
    private static final int MAX_DESCRIPTION_LENGTH = 50;
    private final String appointmentID; // Made final so it cannot be updated
    private LocalDateTime startTime; // These are all private so they can't be
    private LocalDateTime endTime; // accessed or modified directly from outside the class
    private String description;


    public Appointment(LocalDateTime startTime, LocalDateTime endTime, String description) {
        this.appointmentID = generateUniqueID(); // Generates an ID for the appointment

        validateTimes(startTime, endTime);
        validateDescription(description);

        this.startTime = startTime; // These are used to prevent conflicts
        this.endTime = endTime; // between the parameters and instance variables
        this.description = description;


    }
    public void validateAppointmentDetails(LocalDateTime startTime, LocalDateTime endTime, String description) {
        // Checks appointment information without changing the stored values
        validateTimes(startTime, endTime);
        validateDescription(description);
    }

    public void updateAppointmentDetails(LocalDateTime startTime, LocalDateTime endTime, String description) {
        // Validates all of the updated information before changing the appointment
        validateAppointmentDetails(startTime, endTime, description);

        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    private void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            // The appointment must have both a start and end time
            throw new IllegalArgumentException("Start and end times cannot be null");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            // The appointment can't start in the past
            throw new IllegalArgumentException("Start time cannot be in the past");
        }

        if (!endTime.isAfter(startTime)) {
            // The appointment has to end after it starts
            throw new IllegalArgumentException("End time must be after start time");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > MAX_DESCRIPTION_LENGTH) {
            // Description can't be null or greater than 50 characters
            throw new IllegalArgumentException("Invalid description");
        }
    }

    private String generateUniqueID() { // Constructor with an ID generator to
                                        // ensure unique ID's

        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, APPOINTMENT_ID_LENGTH); // Ensures ID isn't more than 10 characters
    }


    public String getAppointmentID() { // Getter for the appointmentID
        return appointmentID; // Returns the ID
    }

    public LocalDateTime getStartTime() { // Getter method for startTime
        return startTime; // Returns the appointment start time
    }

    public LocalDateTime getEndTime() { // Getter method for endTime
        return endTime; // Returns the appointment end time
    }

    public String getDescription() { // Getter method for the description
        return description; // Returns the description

    }
















}
