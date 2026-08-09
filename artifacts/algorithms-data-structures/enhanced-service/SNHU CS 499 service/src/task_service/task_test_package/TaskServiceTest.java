/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */

package task_service.task_test_package;

import org.junit.jupiter.api.Test; // Import for JUnit testing
import java.time.LocalDate; // Import for testing task due dates
import static org.junit.jupiter.api.Assertions.*;
import task_service.task_package.Task; // Imports the Task class
import task_service.task_package.TaskService; // Imports the Task Service class
import java.util.List; // Import for testing task order

public class TaskServiceTest {
    private static final LocalDate VALID_DUE_DATE = LocalDate.now().plusDays(7);

    @Test
    public void testAddTaskSuccessfully() { // Test for successfully adding a task

        TaskService service = new TaskService(); // Creates a new TaskService
        // Creates a new task
        Task task = service.addTask("Task Name", "Task Description", Task.PriorityLevel.HIGH, VALID_DUE_DATE);
        assertNotNull(task.getID()); // Verifies that ID isn't null
        assertEquals("Task Name", task.getName()); // Verifies that the fields are correct
        assertEquals("Task Description", task.getDescription());
        assertEquals(Task.PriorityLevel.HIGH, task.getPriorityLevel()); // Verifies that the priority is correct
        assertEquals(VALID_DUE_DATE, task.getDueDate()); // Verifies that the due date is correct

    }

    @Test
    public void testVerifyUniqueIDs() { // Test for verifying unique ID's
        TaskService service = new TaskService(); // Creates a new TaskService

        // Create 2 new tasks
        Task task1 = service.addTask("Task Uno", "Description Uno", Task.PriorityLevel.HIGH, VALID_DUE_DATE);
        Task task2 = service.addTask("Task Dos", "Description Dos", Task.PriorityLevel.LOW, VALID_DUE_DATE);
        assertNotEquals(task1.getID(), task2.getID()); // Verifies that they aren't the same

    }
    @Test
    public void testDeleteTaskSuccessfully() { // Test for deleting a task

        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Task Name", "Task Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE);// Adds a new task
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
        Task task = service.addTask("Old Name", "Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Add a new task
        service.updateTaskName(task.getID(), "New Name"); // Update the name

        assertEquals("New Name", task.getName()); // Verify the name was updated
    }
    @Test

    public void testUpdateTaskDescriptionSuccessfully() { // Test for updating description succesfully
        TaskService service = new TaskService(); // Creates a new TaskService
        Task task = service.addTask("Name", "Old Description", Task.PriorityLevel.MEDIUM, VALID_DUE_DATE); // Adds a new task
        service.updateTaskDescription(task.getID(), "New Description"); // Updates the description
        assertEquals("New Description", task.getDescription());// Verifies that the description was updated
    }

    @Test
    public void testUpdateTaskPrioritySuccessfully() { // Test for updating priority successfully
        TaskService service = new TaskService();

        Task task = service.addTask("Task Name", "Task Description", Task.PriorityLevel.LOW, VALID_DUE_DATE);

        service.updateTaskPriorityLevel(task.getID(), Task.PriorityLevel.HIGH);
        assertEquals(Task.PriorityLevel.HIGH, task.getPriorityLevel()); // Verifies that priority was updated
    }

    @Test
    public void testUpdateTaskDueDateSuccessfully() { // Test for updating the due date successfully
        TaskService service = new TaskService();
        LocalDate newDueDate = VALID_DUE_DATE.plusDays(7);

        Task task = service.addTask("Task Name", "Task Description",
                Task.PriorityLevel.MEDIUM, VALID_DUE_DATE);
        service.updateTaskDueDate(task.getID(), newDueDate);
        assertEquals(newDueDate, task.getDueDate()); // Verifies that the due date was updated

    }

    @Test
    public void testTasksReturnedInUrgencyOrder() { // Test for PriorityQueue task ordering
        TaskService service = new TaskService();
        Task mediumTask = service.addTask("Medium Task", "Medium priority task",
                Task.PriorityLevel.MEDIUM, VALID_DUE_DATE.minusDays(2));

        Task laterHighTask = service.addTask("Later High", "High priority task",
                Task.PriorityLevel.HIGH, VALID_DUE_DATE.plusDays(2));

        Task soonerHighTask = service.addTask("Sooner High", "High priority task",
                Task.PriorityLevel.HIGH, VALID_DUE_DATE);

        List<Task> orderedTasks = service.getTasksByUrgency();

        assertEquals(3, orderedTasks.size());
        assertEquals(soonerHighTask, orderedTasks.get(0));
        assertEquals(laterHighTask, orderedTasks.get(1));
        assertEquals(mediumTask, orderedTasks.get(2));


    }

    @Test
    public void testUpdateNonexistentTaskThrowsException() { // Test for exceptions from unsuccessful task updates
        TaskService service = new TaskService(); // Creates a new TaskService
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskName("nonexistentID", "New Name"); // Verifies that the exception is thrown
        });

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskDescription("nonexistentID", "New Description");// Verifies that the exception is thrown
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskPriorityLevel("nonexistentID", Task.PriorityLevel.HIGH);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateTaskDueDate("nonexistentID", VALID_DUE_DATE);
        });


    }

    @Test
    public void testTaskFiltering() { // Tests for filtering tasks by priority and due date
        TaskService service = new TaskService();

        Task highTask = service.addTask("High Task", "High priority task",
                Task.PriorityLevel.HIGH, VALID_DUE_DATE.plusDays(2));

        Task mediumTask = service.addTask("Medium Task", "Medium priority task",
                Task.PriorityLevel.MEDIUM, VALID_DUE_DATE);

        service.addTask("Low Task", "Low priority task",
                Task.PriorityLevel.LOW, VALID_DUE_DATE.plusDays(4));

        List<Task> highPriorityTasks =
                service.filterTasksByPriority(Task.PriorityLevel.HIGH);

        assertEquals(1, highPriorityTasks.size());
        assertEquals(highTask, highPriorityTasks.get(0));

        List<Task> tasksDueByDate =
                service.filterTasksDueBy(VALID_DUE_DATE);

        assertEquals(1, tasksDueByDate.size());
        assertEquals(mediumTask, tasksDueByDate.get(0));

    }










}


