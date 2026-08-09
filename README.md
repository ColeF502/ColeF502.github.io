# ColeF502.github.io

## SNHU CS 499 2-2 Milestone One Code Review

This code review examines the original versions of my Travlr Getaways full-stack web application and Contact, Task, and Appointment Services artifacts. It identifies areas for improvement and explains my planned enhancements in software design and engineering, algorithms and data structures, and databases.

### [Watch my Code Review here](https://youtu.be/OL9BIUAW2qg)

-------

## Category One: Software Design and Engineering

### *Travlr Getaways*

### Artifact Overview

Travlr Getaways is a full-stack travel application that I originally created for CS 465: Full Stack Development I in June 2026. The project includes a customer-facing travel website, an Angular administrative application, a Node.js and Express backend, and a MongoDB database used to store trip information. Authenticated administrators can use the administrative side to manage the available trip records.

### Enhancement and Skills Demonstrated

I selected Travlr Getaways because it's one of the larger projects I completed and demonstrates experience with both frontend and backend development. For the software design and engineering enhancement, I combined the separate add-trip and edit-trip components into one reusable trip form. I also added trip deletion with a confirmation step, stronger form validation, loading and result messages, protected Angular routes, improved JWT protection, and centralized configuration values.

These changes demonstrate my skills in full-stack development, reusable Angular component design, authentication and authorization, protected API development, validation, error handling, testing, and code organization. The enhancement made the application more secure and maintainable while also adding functionality that was missing from the original version.

### Reflection

One thing that was reinforced to me when enhancing the artifact was how changing one part of an application can uncover problems in other areas that weren’t exactly obvious before. For instance, I found that the interface wasn’t updating correctly after logging in or out, and I also ran into an issue where changing a trip’s code caused the update request to fail because it was trying to use the new code instead of the original one. One of the bigger challenges was testing because my computer kept running out of memory when all of the tests ran at the same time. I ended up changing the test configuration so the files would run sequentially, which solved the problem.

This enhancement fully met Course Outcomes 4 and 5, which were the outcomes I planned to address in Module One. It met Outcome 4 because I used full-stack development tools, reusable components, centralized configuration, and testing to improve the application and add useful functionality. It met Outcome 5 because I strengthened authentication and authorization, protected the Angular pages and API endpoints, and prevented unauthorized access. I believe that both of my planned outcomes were fully met.

Overall, the enhancement improved the artifact by making it more secure, maintainable, reliable, and complete. I didn’t receive any instructor feedback that required revisions, so I didn’t need to make any additional changes before publishing the enhancement.

### [View Original Travlr Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/software-design/original-travlr)
### [View Enhanced Travlr Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/software-design/enhanced-travlr/SNHU%20CS%20499%20travlr)

## Category Two: Algorithms and Data Structures

### *Contact, Task, and Appointment Services*

### Artifact Overview

Contact, Task, and Appointment Services is a Java application that I originally created for CS 320: Software Testing, Automation, and Quality Assurance in June 2025. The project is made up of separate services used to create, update, delete, organize, and search contacts, tasks, and appointments. It also includes validation, appointment scheduling and conflict detection, task prioritization, and JUnit tests used to verify the program’s functionality.

### Enhancement and Skills Demonstrated

I selected Contact, Task, and Appointment Services because its fairly basic original design gave me a good opportunity to demonstrate how I could choose and apply better data structures for specific problems. The project already included separate services, validation, and testing, but the enhanced version now uses a TreeMap to keep appointments ordered by their start times and a PriorityQueue to organize tasks by priority and due date. It also includes scheduling conflict detection, date and time searches for appointments, and filtering options for tasks.

These changes demonstrate my skills in Java, algorithms and data structures, searching, sorting, filtering, conflict detection, validation, testing, and code organization. The enhancement made the services more useful and efficient by adding better ways to organize and retrieve data, while also expanding the original functionality with appointment scheduling, task prioritization, named constants, and additional JUnit testing for the new features and edge cases.

### Reflection

One thing that was reinforced to me while enhancing the artifact was how changing or adding a data structure can affect several other parts of a program. For instance, I kept the original HashMaps so appointments and tasks could still be found quickly by their IDs, but this meant I also had to make sure the TreeMap and PriorityQueue stayed updated whenever something was added, deleted, or changed. I also gained more experience with scheduling conflict detection, date and time searches, filtering, and updating existing tests after changing the appointment and task classes. One of the main challenges was making all of these enhancements without breaking the original contact, task, and appointment functionality, so I updated the tests as I worked and ultimately got all 59 to pass.

This enhancement fully met Course Outcomes 1, 3, and 4, which were the outcomes I planned to address in Module One. It met Outcome 1 through cleaner code organization, named constants, testing, and design choices that make the program easier for another developer to understand and maintain. It met Outcome 3 by using TreeMap, PriorityQueue, searching, filtering, and scheduling conflict detection because these required choosing solutions that fit specific problems and considering the trade-offs involved. It met Outcome 4 with the Java implementation, validation, testing, and additional functionality added to the services. I believe that all three of my planned outcomes were fully met.

Overall, the enhancement improved the artifact by making the services more organized, useful, and efficient while adding functionality for appointment scheduling, searching, task prioritization, and filtering. I didn’t receive any instructor feedback that required revisions, so I didn’t need to make any additional changes before publishing the enhancement.

### [View Original Service Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/algorithms-data-structures/original-service/service%20-%20Original)

### [View Enhanced Service Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/algorithms-data-structures/enhanced-service/SNHU%20CS%20499%20service)
