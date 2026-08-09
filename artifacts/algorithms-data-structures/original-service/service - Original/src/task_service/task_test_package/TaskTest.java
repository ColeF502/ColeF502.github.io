/*
Cole Fredericks
6/12/25
SNHU CS 320
6-1 Project One
 */

package task_service.task_test_package;

import org.junit.jupiter.api.Test; // import for JUnit testing
import static org.junit.jupiter.api.Assertions.*;
import task_service.task_package.Task; // Imports the Task class


public class TaskTest {


    @Test
    public void testValidTaskCreation() { // Tests for valid task creation
        Task task = new Task("12345", "Task Name", "Task Description");
        assertEquals("12345", task.getID()); // Is not null and isn't greater than 10 characters
        assertEquals("Task Name", task.getName()); // Isn't null and isn't greater than 20 characters
        assertEquals("Task Description", task.getDescription());// Isn't null and isn't greater than 50 characters
    }
    @Test
    public void testGeneratedID() { // Test for the ID generator

        Task task = new Task("Task Name", "Task Description");
        assertNotNull(task.getID()); // Checks that the ID isn't null

    }


    @Test
    public void testInvalidID() { // Test for ID that is not null and is not greater than 1o characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678900000", "Task Name", "Task Description"); // Test for length
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Task Name", "Task Description"); // Test for null

        });
    }

    @Test
    public void testInvalidName() { // Test for name that is not null and is not greater than 20 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", null, "Task Description"); // Test for null
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "A name that is mosttttt definitely toooooo long to be accepted", "Task Description");
        });											// Test for length
    }


    @Test

    public void testInvalidDescription() { // Test for description that is not null and is not greater than 50 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "Task Name", null); // Test for null
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "Task Name", "A description that's wayyyyyyyyyyyyyy too long. " +
                    "I believe it totally, definitelyyyyy exceeds the fiftyyyyyyy characters limit.");
        });												// Test for length

    }


    @Test
    public void testSettersWithValidInputs() { // Test for the setters

        Task task = new Task("12345", "Task Name", "Task Description");

        task.setName("New Name"); // Set name
        assertEquals("New Name", task.getName());

        task.setDescription("New Description"); // Set description
        assertEquals("New Description", task.getDescription());

    }

    @Test
    public void testSettersWithInvalidInputs() { // Test for the setters with invlid inputs

        Task task = new Task("12345", "Task Name", "Task Description");

        assertThrows(IllegalArgumentException.class, () -> task.setName(null)); // Null name
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("This description is clearly wayyyyyyy " +
                "tooooooo long and is most definitely not a description of any kind or anything. It's just a rambling bunch of nothing!"));
                        // Wayyy too many characters



    }







}

