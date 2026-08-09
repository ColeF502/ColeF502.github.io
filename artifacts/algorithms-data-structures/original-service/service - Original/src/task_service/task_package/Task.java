/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package task_service.task_package;

import java.util.UUID; // Import for generating random ID's


public class Task {
    private final String ID; // String ID is declared final so it cannot be updated

    private String name; // String ID, name and description are declared private so they cannot -
    private String description; // be accessed or modified directly from outside the class


    public Task(String ID, String name, String description) {
        if (ID == null || ID.length() > 10) {
            // If ID is null or is more than 10 characters, an exception is thrown

            throw new IllegalArgumentException("Invalid ID. Cannot be null or greater than 10 characters.");
        }


        if (name == null || name.length() > 20) {
            // If name is null or greater than 20 characters, an exception is thrown

            throw new IllegalArgumentException("Invalid name. Cannot be null or greater than 20 characters.");
        }

        if (description == null || description.length() > 50) {
            // If description is null or more than 50 characters, an exception is thrown
            throw new IllegalArgumentException("Invalid description. Cannot be null or greater than 50 characters.");


        }


        this.ID = ID;  				 // These are used to prevent
        this.name = name; 			 // conflicts between the parameters
        this.description = description;// and the instance variables


    }

    public Task(String name, String description) {
        this(generateUniqueID(), name, description); // Feedback from 3-2 requested a generator for ID
                                                    // This is a constructor with an ID generator
                                                     // I'm hoping I did it correctly this time
    }

    public static String generateUniqueID() { // ID generator that creates a random 10 digit ID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 10);
    }

    public String getID() { // Getter method for ID
        return ID;
    }

    public String getName() { //Getter method for name
        return name;

    }
    public void setName(String name) { // Setter for name
        if (name == null || name.length() > 20) {
            // If the input is null or more than 20, an exception is thrown
            throw new IllegalArgumentException("Invalid name. Cannot be null or greater than 20 characters.");
        }

        this.name = name; // If neither are true, then the input is assigned to name

    }

    public String getDescription() {// Getter method for description
        return description;
    }

    public void setDescription(String description) { // Setter for description

        if (description == null || description.length() > 50) {
            // If the input is null ot greater than 50, an exception is thrown
            throw new IllegalArgumentException("Invalid description. Cannot be null or greater than 50 characters.");
        }

        this.description = description; // If neither are true, the input is assigned to description
    }








}
