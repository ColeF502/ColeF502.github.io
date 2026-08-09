/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package task_service.task_package;

import java.util.Map; // These are used to store key-value pairs
import java.util.HashMap;



public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>(); // Made final so the map cannot be changed
                                                                // This creates a hashmap for storage

    public Task addTask(String name, String description) { // Way to add a task
        String uniqueID;
        do {
            uniqueID = Task.generateUniqueID(); // Calls the Task class to generate a 10-character random ID
        } while (tasks.containsKey(uniqueID)); // Checks to see if the ID already exists


        Task task = new Task(uniqueID, name, description); // Creates a new task with the unique ID
        tasks.put(uniqueID, task); // Adds the task to the map

        return task;// Returns the new task


    }


    public void deleteTask(String ID) { // way to delete a task
        if (!tasks.containsKey(ID)) { // Checks the tasks map to see if the ID already exists

            throw new IllegalArgumentException("Task with ID #" + ID + " not found."); // If the ID doesn't exist
                                                                                        // and exception is thrown
        }

        tasks.remove(ID); // Deletes the ID if it's found
    }

    public void updateTaskName(String ID, String newName) { // Way to update the name of a task
        Task task = findTaskByID(ID); // Looks for the task by ID, if it isn't found,
                                        // it throws an exception using findTaskByID
        task.setName(newName); // If the task exists, name is replaced with newName

    }



    public void updateTaskDescription(String ID, String newDescription) { // Way to update description of a task

        Task task = findTaskByID(ID); // Looks for the task by ID, if it isn't found,
                                    // it throws an exception using findTaskByID
        task.setDescription(newDescription);// If the task exists, description is replaced with newDescription


    }
    private Task findTaskByID(String ID) { // Way to find a task in the map using its ID
        Task task = tasks.get(ID);
        if (task == null) { // If the task's ID isn't found, it throws an exception
            throw new IllegalArgumentException("Task with ID #" + ID + " not found.");
        }

        return task; // Returns the task if it's found


    }









}
