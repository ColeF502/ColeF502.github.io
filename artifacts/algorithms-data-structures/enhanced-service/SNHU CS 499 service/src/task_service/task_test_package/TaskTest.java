/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */

package task_service.task_test_package;

import org.junit.jupiter.api.Test; // import for JUnit testing
import static org.junit.jupiter.api.Assertions.*;
import task_service.task_package.Task; // Imports the Task class

import java.time.LocalDate; // Import for testing task due dates


public class TaskTest {
    private static final LocalDate VALID_DUE_DATE = LocalDate.now().plusDays(7);


    @Test
    public void testValidTaskCreation() { // Tests for valid task creation
        Task task = new Task("12345", "Task Name", "Task Description",
                Task.PriorityLevel.HIGH, VALID_DUE_DATE);
        assertEquals("12345", task.getID()); // Is not null and isn't greater than 10 characters
        assertEquals("Task Name", task.getName()); // Isn't null and isn't greater than 20 characters
        assertEquals("Task Description", task.getDescription());// Isn't null and isn't greater than 50 characters
        assertEquals(Task.PriorityLevel.HIGH, task.getPriorityLevel()); // Confirms the priority level matches
        assertEquals(VALID_DUE_DATE, task.getDueDate()); // Confirms the due date matches
    }
    @Test
    public void testGeneratedID() { // Test for the ID generator

        Task task = new Task("Task Name", "Task Description",
                Task.PriorityLevel.MEDIUM, VALID_DUE_DATE);
        assertNotNull(task.getID()); // Checks that the ID isn't null

    }


    @Test
    public void testInvalidID() { // Test for ID that it's not null and isn't greater than 10 characters

        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678900000", "Task Name", "Task Description",
                    Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for length
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Task Name", "Task Description",
                    Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for null
        });
    }

    @Test
    public void testInvalidName() { // Test for name that isn't null and isn't greater than 20 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", null, "Task Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for null
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "A name that is mosttttt definitely toooooo long to be accepted",
                    "Task Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for length
        });
    }



    @Test
    public void testInvalidDescription() { // Test for description that is not null and is not greater than 50 characters


        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "Task Name", null, Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for null
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345", "Task Name", "A description that's wayyyyyyyyyyyyyy too long. " +
                            "I believe it totally, definitelyyyyy exceeds the fiftyyyyyyy characters limit.",
                    Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Test for length
        });
    }

    @Test
    public void testInvalidPriorityLevel() { // Test for a priority level that's null

        assertThrows(IllegalArgumentException.class, () ->
                new Task("12345", "Task Name", "Task Description", null, VALID_DUE_DATE)
        );
    }

    @Test
    public void testInvalidDueDate() { // Test for null and past due dates
        assertThrows(IllegalArgumentException.class, () ->
                new Task("12345", "Task Name", "Task Description", Task.PriorityLevel.MEDIUM, null)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Task("12345", "Task Name", "Task Description", Task.PriorityLevel.MEDIUM,
                        LocalDate.now().minusDays(1))
        );
    }
    @Test
    public void testSettersWithValidInputs() { // Test for the setters

        Task task = new Task("12345", "Task Name", "Task Description", Task.PriorityLevel.LOW, VALID_DUE_DATE);

        task.setName("New Name"); // Set name
        assertEquals("New Name", task.getName());

        task.setDescription("New Description"); // Set description
        assertEquals("New Description", task.getDescription());

        task.setPriorityLevel(Task.PriorityLevel.HIGH); // Sets the priority level
        assertEquals(Task.PriorityLevel.HIGH, task.getPriorityLevel());

        LocalDate newDueDate = VALID_DUE_DATE.plusDays(7);
        task.setDueDate(newDueDate); // Sets the due date
        assertEquals(newDueDate, task.getDueDate());

    }

    @Test
    public void testSettersWithInvalidInputs() { // Test for the setters with invlid inputs
        Task task = new Task("12345", "Task Name", "Task Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE);

        assertThrows(IllegalArgumentException.class, () -> task.setName(null)); // Null name
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("This description is clearly wayyyyyyy " +
                "tooooooo long and is most definitely not a description of any kind or anything. It's just a rambling bunch of nothing!"));
                        // Wayyy too many characters
        assertThrows(IllegalArgumentException.class, () -> task.setPriorityLevel(null)); // Null priority level

        assertThrows(IllegalArgumentException.class, () -> task.setDueDate(null)); // Null due date

        assertThrows(IllegalArgumentException.class, () ->
                task.setDueDate(LocalDate.now().minusDays(1))); // Due date in the past



    }







}

