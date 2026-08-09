/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */


package appointment_service.appointment_package;

import java.util.HashMap; // These are used to store key-value pairs
import java.util.Map;
import java.time.LocalDateTime; // Import for ordering appointments by start time
import java.util.ArrayList; // Import for storing appointments with the same start time
import java.util.List; // Import for appointment lists
import java.util.TreeMap; // Import for keeping appointments in chronological order
import java.time.LocalDate; // Import for searching appointments by date
import java.time.LocalTime; // Import for searching appointments by time


public class AppointmentService {
    private final Map<String, Appointment> appointments; // Made final so the map cannot be changed

    private final TreeMap<LocalDateTime, List<Appointment>> appointmentsByStartTime;

    public AppointmentService() {
        appointments = new HashMap<>(); // Creates a hashmap for storage

        appointmentsByStartTime = new TreeMap<>(); // Keeps appointments ordered by their start time

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
        if (hasSchedulingConflict(appointment.getStartTime(),
                appointment.getEndTime(), null)) {
            // Prevents an appointment from overlapping an existing appointment
            throw new IllegalArgumentException("Appointment conflicts with an existing appointment");
        }

        appointments.put(id, appointment); // If neither are true, the appointment is added to the map


        addAppointmentToStartTimeMap(appointment);

    }



    public void deleteAppointment(String appointmentID) { // Way to delete an appointment
        if (appointmentID == null || !appointments.containsKey(appointmentID)) {
            // If the appointment ID is null or the ID
            // cannot be found and exception is thrown
            throw new IllegalArgumentException("Appointment ID does not exist");
        }

        Appointment appointment = appointments.remove(appointmentID); // Removes and retrieves the appointment
        removeAppointmentFromStartTimeMap(appointment);
    }
    public void updateAppointment(String appointmentID, LocalDateTime startTime, LocalDateTime endTime, String description) {
        if (appointmentID == null || !appointments.containsKey(appointmentID)) {
            // The appointment has to exist before it can be updated
            throw new IllegalArgumentException("Appointment ID does not exist");
        }

        Appointment appointment = appointments.get(appointmentID);

        // Checks the new values before anything is changed
        appointment.validateAppointmentDetails(startTime, endTime, description);


        if (hasSchedulingConflict(startTime, endTime, appointmentID)) {
            // Prevents an updated appointment from overlapping another appointment
            throw new IllegalArgumentException("Appointment conflicts with an existing appointment");
        }

        // Removes the old start time before changing the appointment
        removeAppointmentFromStartTimeMap(appointment);

        appointment.updateAppointmentDetails(startTime, endTime, description);

        // Adds the appointment back using its new start time
        addAppointmentToStartTimeMap(appointment);
    }

    public List<Appointment> getAppointmentsInOrder() {
        List<Appointment> orderedAppointments = new ArrayList<>();

        // TreeMap values are already arranged by their start times
        for (List<Appointment> appointmentsAtTime : appointmentsByStartTime.values()) {
            orderedAppointments.addAll(appointmentsAtTime);
        }

        return orderedAppointments;

    }

    public List<Appointment> searchAppointmentsByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            // Need both dates before the search can be completed
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        if (endDate.isBefore(startDate)) {
            // The end date can't come before the start date
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        LocalDateTime rangeStart = startDate.atStartOfDay();
        LocalDateTime rangeEnd = endDate.plusDays(1).atStartOfDay();

        List<Appointment> matchingAppointments = new ArrayList<>();

        // Retrieves appointments that start on any date within the selected range
        for (List<Appointment> appointmentsAtTime :
                appointmentsByStartTime.subMap(rangeStart, true, rangeEnd, false).values()) {
            matchingAppointments.addAll(appointmentsAtTime);
        }

        return matchingAppointments;
    }
    public List<Appointment> searchAppointmentsByTimeRange(LocalDate selectedDate, LocalTime startTime, LocalTime endTime) {
        if (selectedDate == null || startTime == null || endTime == null) {
            // Date and both times are needed before the search can be completed
            throw new IllegalArgumentException("Date and times cannot be null");
        }

        if (!endTime.isAfter(startTime)) {
            // The end time has to come after the start time
            throw new IllegalArgumentException("End time must be after start time");

        }

        LocalDateTime rangeStart = selectedDate.atTime(startTime);
        LocalDateTime rangeEnd = selectedDate.atTime(endTime);

        List<Appointment> matchingAppointments = new ArrayList<>();

        // Gets appointments that start within the selected time range
        for (List<Appointment> appointmentsAtTime :
                appointmentsByStartTime.subMap(rangeStart, true, rangeEnd, false).values()) {
            matchingAppointments.addAll(appointmentsAtTime);
        }

        return matchingAppointments;
    }

    private boolean hasSchedulingConflict(LocalDateTime startTime, LocalDateTime endTime, String excludedAppointmentID) {
        for (Appointment existingAppointment : appointments.values()) {

            // Ignores the appointment being updated
            if (excludedAppointmentID != null &&
                    existingAppointment.getAppointmentID().equals(excludedAppointmentID)) {
                continue;
            }

            boolean overlaps =
                    startTime.isBefore(existingAppointment.getEndTime()) &&
                            endTime.isAfter(existingAppointment.getStartTime());
            if (overlaps) {
                return true;
            }
        }

        return false;
    }

    private void addAppointmentToStartTimeMap(Appointment appointment) {
        // Creates a list for the start time if one doesn't already exist
        appointmentsByStartTime
                .computeIfAbsent(appointment.getStartTime(),
                        startTime -> new ArrayList<>())
                .add(appointment);
    }

    private void removeAppointmentFromStartTimeMap(Appointment appointment) {
        List<Appointment> appointmentsAtTime =
                appointmentsByStartTime.get(appointment.getStartTime());

        appointmentsAtTime.remove(appointment);

        if (appointmentsAtTime.isEmpty()) {
            // Removes the start time when it no longer contains any appointments
            appointmentsByStartTime.remove(appointment.getStartTime());
        }
    }







}

