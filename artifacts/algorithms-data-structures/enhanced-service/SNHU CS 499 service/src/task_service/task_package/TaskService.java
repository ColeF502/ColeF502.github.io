/*
Cole Fredericks
7/25/26
SNHU CS 499
4-2 Milestone Three
 */

package task_service.task_package;

import java.time.LocalDate; // Import for task due dates
import java.util.Map; // These are used to store key-value pairs
import java.util.HashMap;
import java.util.ArrayList; // Import for returning tasks in urgency order
import java.util.Comparator; // Import for defining how task urgency is compared
import java.util.List; // Import for task lists
import java.util.PriorityQueue; // Import for ordering tasks by urgency



public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>(); // Made final so the map cannot be changed
                                                                // This creates a hashmap for storage

    private final PriorityQueue<Task> tasksByUrgency = new PriorityQueue<>(
            Comparator.comparing(Task::getPriorityLevel).thenComparing(Task::getDueDate).thenComparing(Task::getID)
    );

    public Task addTask(String name, String description, Task.PriorityLevel priorityLevel,
                        LocalDate dueDate) { // Way to add a task
        String uniqueID;
        do {
            uniqueID = Task.generateUniqueID(); // Calls the Task class to generate a 10-character random ID
        } while (tasks.containsKey(uniqueID)); // Checks to see if the ID already exists


        Task task = new Task(uniqueID, name, description, priorityLevel, dueDate); // Creates a new task
        tasks.put(uniqueID, task); // Adds the task to the map
        tasksByUrgency.add(task); // Adds the task to the priority queue

        return task;// Returns the new task


    }


    public void deleteTask(String ID) { // way to delete a task
        if (!tasks.containsKey(ID)) { // Checks the tasks map to see if the ID already exists

            throw new IllegalArgumentException("Task with ID #" + ID + " not found."); // If the ID doesn't exist
                                                                                        // and exception is thrown
        }

        Task task = tasks.remove(ID); // Removes and gets the task
        tasksByUrgency.remove(task); // Removes the task from the priority queue
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
    public void updateTaskPriorityLevel(String ID, Task.PriorityLevel newPriorityLevel) {
        // Finds the task before updating its priority level
        Task task = findTaskByID(ID);
        task.setPriorityLevel(newPriorityLevel);

        // Removes and adds the task again so the queue can put it in its new position
        tasksByUrgency.remove(task);
        tasksByUrgency.add(task);
    }

    public void updateTaskDueDate(String ID, LocalDate newDueDate) {
        // Finds the task before updating its due date
        Task task = findTaskByID(ID);
        task.setDueDate(newDueDate);
        // Removes and adds the task again so the queue can put it in its new position
        tasksByUrgency.remove(task);
        tasksByUrgency.add(task);
    }

    public List<Task> getTasksByUrgency() {
        PriorityQueue<Task> queueCopy = new PriorityQueue<>(tasksByUrgency);
        List<Task> orderedTasks = new ArrayList<>();

        // Polling the copy returns each task from most to least urgent
        while (!queueCopy.isEmpty()) {
            orderedTasks.add(queueCopy.poll());
            // Using a copy because polling the original queue would remove the actual stored tasks
        }

        return orderedTasks;
    }

    public List<Task> filterTasksByPriority(Task.PriorityLevel priorityLevel) {
        if (priorityLevel == null) {
            // A priority level is needed before the tasks can be filtered
            throw new IllegalArgumentException("Priority level cannot be null.");
        }
        List<Task> matchingTasks = new ArrayList<>();

        // Check the tasks in urgency order and keeps the matching priority
        for (Task task : getTasksByUrgency()) {
            if (task.getPriorityLevel() == priorityLevel) {
                matchingTasks.add(task);
            }


        }

        return matchingTasks;
    }

    public List<Task> filterTasksDueBy(LocalDate dueDate) {

        if (dueDate == null) {
            // A due date is needed before the tasks can be filtered
            throw new IllegalArgumentException("Due date cannot be null.");
        }

        List<Task> matchingTasks = new ArrayList<>();
        // Keeps tasks due on or before the selected date
        for (Task task : getTasksByUrgency()) {
            if (!task.getDueDate().isAfter(dueDate)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    private Task findTaskByID(String ID) { // Way to find a task in the map using its ID
        Task task = tasks.get(ID);
        if (task == null) { // If the task's ID isn't found, it throws an exception
            throw new IllegalArgumentException("Task with ID #" + ID + " not found.");
        }

        return task; // Returns the task if it's found


    }









}
