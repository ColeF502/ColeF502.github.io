/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package task_service.task_test_package;

import org.junit.jupiter.api.Test; // Import for JUnit testing

import static org.junit.jupiter.api.Assertions.*;
import task_service.task_package.Task; // Imports the Task class
import task_service.task_package.TaskService; // Imports the Task Service class

public class TaskServiceTest {

    @Test
    public void testAddTaskSuccessfully() { // Test for successfully adding a task

        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Task Name", "Task Description"); // Adds a new task
        assertNotNull(task.getID()); // Verifies that ID isn't null
        assertEquals("Task Name", task.getName()); // Verifies that the fields are correct
        assertEquals("Task Description", task.getDescription());

    }

    @Test
    public void testVerifyUniqueIDs() { // Test for verifying unique ID's
        TaskService service = new TaskService(); // Creates a new TaskService

        Task task1 = service.addTask("Task Uno", "Description Uno"); // Creates 2 new tasks
        Task task2 = service.addTask("Task Dos", "Description Dos");
        assertNotEquals(task1.getID(), task2.getID()); // Verifies that they aren't the same

    }
    @Test
    public void testDeleteTaskSuccessfully() { // Test for deleting a task
        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Task Name", "Task Description"); // Adds a new task
        assertDoesNotThrow(() -> service.deleteTask(task.getID())); // Verify that the task was deleted
                                                                // was deleted without thrwoing an exception


    }

    @Test
    public void testDeleteNonexistentTaskThrowsException() { // Test for exception thrown from unsuccessful deletion
        TaskService service = new TaskService(); // Creates a new TaskService

        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("nonexistentID"); // Verifies that the exception is thrown

        });
    }




    @Test
    public void testUpdateTaskNameSuccessfully() { // Test for updating name successfully
        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Old Name", "Description"); // Add a new task
        service.updateTaskName(task.getID(), "New Name"); // Update the name

        assertEquals("New Name", task.getName()); // Verify the name was updated
    }
    @Test

    public void testUpdateTaskDescriptionSuccessfully() { // Test for updating description succesfully
        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Name", "Old Description"); // Adds a new task
        service.updateTaskDescription(task.getID(), "New Description"); // Updates the description
        assertEquals("New Description", task.getDescription());// Verifies that the description was updated
    }

    @Test
    public void testUpdateNonexistentTaskThrowsException() { // Test for exception thrown from unsuccessful
                                                                // updates to name and description
        TaskService service = new TaskService(); // Creates a new TaskService
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskName("nonexistentID", "New Name"); // Verifies that the exception is thrown
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskDescription("nonexistentID", "New Description");// Verifies that the exception is thrown
        });


    }










}


