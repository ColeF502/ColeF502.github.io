# ColeF502.github.io

---

## Professional Self-Assessment

One of the first classes I took for my program was IT 140 Introduction to Scripting. In that course, I had to develop a text-based game, and at the time I was very proud of it. Recently, I was showing a friend’s kid the game so they could try it, and I realized just how many issues it had, and very obvious issues at that. One big issue was the way the window would automatically close once the game was won. Another issue was that if there was a leading space or if you didn’t capitalize the first letter, it would reject the input. So, I actually took some time to fix the text-based game up a bit for my friend’s kid and there were just so many obvious things that I would have never done if I had made that game today. Another course that really stood out to me was CS 319 UI/UX Design and Development where I developed a weight-tracking app. I was quite proud of that one as well. However, since then I have been trying to think of endless possibilities for another app that would actually serve a purpose. Lately I’ve been thinking of simply making a game app with little to no ads since almost everything these days you get bombarded with constant ads to an infuriating degree. So, my thinking is that if I can make a game or app that is just as good, but it has half the ads or even less, it should be a no-brainer for people to switch to it and since I have no overhead like a big app developer would, I could settle for a small upfront charge or one or two ads. Developing the ePortfolio for CS 499 Computer Science Capstone also gave me another opportunity to look back at some of my previous projects, improve them with the skills I’ve gained throughout the program, and then see just how much my abilities have grown.

Looking back at projects like these, I’d say one of the biggest ways I’ve changed throughout the program is simply how I look at software much differently now. Instead of just thinking about whether something works, I’m constantly thinking about how it could be improved, how easy or hard it’d be to maintain, whether people would actually want to use it, how it might be competing with similar products, and even how it could realistically make money. I think this has helped me recognize that some of my strengths are problem-solving, being meticulous, and constantly looking for ways to improve something instead of just accepting something because it’s good enough. It’s also helped shape my professional values because I want my software to be reliable, useful, and easy for people to use instead of just technically functioning. My goal of becoming a software engineer has also become more defined throughout the program, particularly my interest in eventually working with software for aircraft, spacecraft, or other aerospace systems. The amount of care that’s needed for reliability, testing, security, and maintainability throughout software development has only reinforced my interest in pursuing a career in aerospace.

Throughout the program, I’ve also developed a much broader range of skills than I had when I started. I do have previous experience working in team environments, except it wasn’t in the software development field. The only real collaborating and communicating I did in the program was the weekly discussions throughout, but I wouldn’t call those very collaboration or communication heavy. That being said, when it comes time to communicate and collaborate in a development environment, I don’t foresee any difficulties as I’ve never had issues with those kinds of things in my past. The biggest aspect is simply listening to your co-workers and ensuring everyone is on the same page, and I believe I can do both of those effectively. Communicating with stakeholders would also require me to explain technical information to people who might not have a technical background. Since I myself was one of those people not very long ago, I believe I could explain things in such a way that almost anyone could understand, while also comprehending what a client may actually be looking for from the software. I’ve also become much more comfortable with data structures and algorithms and with understanding that there are often several ways to solve the same problem, but the correct choice is really going to be dependent upon the situation. Software engineering and databases taught me how all the different pieces of an application need to work together and just how important maintainability, testing, validation, and reliability are. In the beginning of the program, security was basically something I treated as an afterthought, but as I’ve progressed, I’ve realized the unwavering need to think about it throughout the entire development process. Overall, I believe the program has made me ready to work in software development because I’m now able to approach projects from a plethora of perspectives instead of just focusing on the code itself.

The technical work that follows this self-assessment includes my code review, which explains my planned enhancements, and my artifacts, which include my Travlr Getaways full-stack application and my Contact, Task, and Appointment Services Java project. The code review shows how I can evaluate existing software, identify problems and areas for improvement, and explain why certain changes would make each project better. Travlr Getaways demonstrates my experience with full-stack development, software design, security, and databases, while the Contact, Task, and Appointment Services project shows more of my work with Java, data structures, algorithms, testing, and program organization. Altogether, these pieces show different sides of the software development process and demonstrate how my current abilities go well beyond simply writing code and include designing, testing, improving, securing, organizing, and maintaining software.

---

## SNHU CS 499 2-2 Milestone One Code Review

This code review examines the original versions of my Travlr Getaways full-stack web application and Contact, Task, and Appointment Services artifacts. It identifies areas for improvement and explains my planned enhancements in software design and engineering, algorithms and data structures, and databases.

### [Watch my Code Review here](https://youtu.be/OL9BIUAW2qg)

---

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

---

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

---

## Category Three: Databases

### *Travlr Getaways*

### Artifact Overview

Travlr Getaways is a full-stack travel application that I originally created for CS 465: Full Stack Development I in June 2026. The application combines a customer-facing travel website with an Angular administrative application, while a Node.js and Express backend connects the different parts of the project to a MongoDB database through Mongoose. The database was originally used primarily for storing and managing trip information, and authenticated administrators can use the administrative side of the application to manage those records.

### Enhancement and Skills Demonstrated

I selected Travlr Getaways for the database category because it gave me an opportunity to demonstrate more than just a basic database that stores records. The original application already used MongoDB and Mongoose to store trip information, but its database functionality was fairly limited. For the enhancement, I expanded the database by adding customer and reservation data, connecting reservations to the correct customer and trip, and tracking trip capacity and remaining availability. I also added duplicate reservation and overbooking prevention, stronger schema validation, timestamps, database indexes, improved error handling, and a MongoDB aggregation that calculates booking totals and estimated revenue.

These changes demonstrate my skills in MongoDB, Mongoose, database and schema design, working with related records, validation, indexing, database testing, aggregation, and error handling. The enhancement made the database more useful and reliable by adding better ways to manage and search related customer, trip, and reservation data while preventing invalid records, duplicate reservations, and overbooking.

### Reflection

One thing that was reinforced to me while enhancing the database was how changes to one part of an application can cause several unforeseen effects in other parts that depend on the same data. Adding reservations required more than simply creating new records because the system also had to connect the correct customer and trip, update remaining availability, and handle reservation failures. One big challenge was preventing overbooking while also ensuring a trip’s availability was restored if a reservation couldn’t be completed. I also ran into smaller issues with MongoDB indexes and testing the database without changing the actual trip and reservation records, which reinforced just how important testing and iteration are.

This enhancement fully met Course Outcomes 3 and 4, which were the outcomes I planned to address in Module One. It met Outcome 3 with the design of the reservation system and the way customer, trip, and reservation data all work together while also preventing problems like duplicate reservations and overbooking. It met Outcome 4 through my use of MongoDB, Mongoose, schema validation, aggregation, indexing, and testing to add useful database functionality to the application. I believe that both of my planned outcomes were fully met.

Overall, the enhancement improved the artifact by making the database more complete, reliable, and useful with the addition of customer and reservation data, booking controls, stronger validation, and database analysis features. I didn’t receive any instructor feedback that required revisions, so I didn’t need to make any additional changes before publishing the enhancement.

### [View Original Travlr Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/databases/original-travlr/travlr%20-%20Original)

### [View Enhanced Travlr Source Code here](https://github.com/ColeF502/ColeF502.github.io/tree/main/artifacts/databases/enhanced-travlr/SNHU%20CS%20499%20travlr)
