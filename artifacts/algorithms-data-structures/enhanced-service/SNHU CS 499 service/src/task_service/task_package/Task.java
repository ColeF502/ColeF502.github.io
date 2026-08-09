/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */

package task_service.task_package;
import java.time.LocalDate; // Import for storing task due dates
import java.util.UUID; // Import for generating random ID's


public class Task {
    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 50;

    public enum PriorityLevel {
        HIGH,
        MEDIUM,
        LOW
    }

    private final String ID; // String ID is declared final so it cannot be updated

    private String name; // String ID, name and description are declared private so they cannot -
    private String description; // be accessed or modified directly from outside the class
    private PriorityLevel priorityLevel; // Stores how urgent the task is
    private LocalDate dueDate; // Stores when the task is due


    public Task(String ID, String name, String description, PriorityLevel priorityLevel, LocalDate dueDate) {
        if (ID == null || ID.length() > MAX_ID_LENGTH) {
            // If ID is null or is more than 10 characters, an exception is thrown

            throw new IllegalArgumentException("Invalid ID. Cannot be null or greater than 10 characters.");
        }


        if (name == null || name.length() > MAX_NAME_LENGTH) {
            // If name is null or greater than 20 characters, an exception is thrown

            throw new IllegalArgumentException("Invalid name. Cannot be null or greater than 20 characters.");
        }

        if (description == null || description.length() > MAX_DESCRIPTION_LENGTH) {
            // If description is null or more than 50 characters, an exception is thrown
            throw new IllegalArgumentException("Invalid description. Cannot be null or greater than 50 characters.");


        }
        if (priorityLevel == null) {
            // Every task needs a valid priority level
            throw new IllegalArgumentException("Priority level cannot be null.");
        }

        if (dueDate == null || dueDate.isBefore(LocalDate.now())) {
            // The due date can't be null or is already in the past
            throw new IllegalArgumentException("Due date cannot be null or in the past.");
        }


        this.ID = ID;  				 // These are used to prevent
        this.name = name; 			 // conflicts between the parameters
        this.description = description;// and the instance variables
        this.priorityLevel = priorityLevel;
        this.dueDate = dueDate;


    }

    public Task(String name, String description, PriorityLevel priorityLevel, LocalDate dueDate) {
        this(generateUniqueID(), name, description, priorityLevel, dueDate);
        // This constructor generates the ID before creating the task
    }

    public static String generateUniqueID() { // ID generator that creates a random 10 digit ID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, MAX_ID_LENGTH);
    }

    public String getID() { // Getter method for ID
        return ID;
    }

    public String getName() { //Getter method for name
        return name;

    }
    public void setName(String name) { // Setter for name
        if (name == null || name.length() > MAX_NAME_LENGTH) {
            // If the input is null or more than 20, an exception is thrown
            throw new IllegalArgumentException("Invalid name. Cannot be null or greater than 20 characters.");
        }

        this.name = name; // If neither are true, then the input is assigned to name

    }

    public String getDescription() {// Getter method for description
        return description;
    }

    public PriorityLevel getPriorityLevel() { // Getter method for priorityLevel
        return priorityLevel;
    }

    public LocalDate getDueDate() { // Getter method for dueDate
        return dueDate;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        if (priorityLevel == null) {
            // The priority level can't be removed from the task
            throw new IllegalArgumentException("Priority level cannot be null.");
        }

        this.priorityLevel = priorityLevel;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null || dueDate.isBefore(LocalDate.now())) {
            // The due date can't be null or already in the past
            throw new IllegalArgumentException("Due date cannot be null or in the past.");
        }

        this.dueDate = dueDate;
    }

    public void setDescription(String description) { // Setter for description

        if (description == null || description.length() > MAX_DESCRIPTION_LENGTH) {
            // If the input is null ot greater than 50, an exception is thrown
            throw new IllegalArgumentException("Invalid description. Cannot be null or greater than 50 characters.");
        }

        this.description = description; // If neither are true, the input is assigned to description
    }








}
