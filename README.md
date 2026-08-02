# ColeF502.github.io

## SNHU CS 499 2-2 Milestone One Code Review

### This code review examines the original versions of my Travlr Getaways full-stack web application and Contact, Task, and Appointment Services artifacts. It identifies areas for improvement and explains my planned enhancements in software design and engineering, algorithms and data structures, and databases.

## [Watch my Code Review here](https://youtu.be/OL9BIUAW2qg)

## Category One: Software Design and Engineering

### Travlr Getaways

### Artifact Overview

Travlr Getaways is a full-stack travel application that I originally created for CS 465: Full Stack Development I in June 2026. The project includes a customer-facing travel website, an Angular administrative application, a Node.js and Express backend, and a MongoDB database used to store trip information. Authenticated administrators can use the administrative side to manage the available trip records.

### Enhancement and Skills Demonstrated

I selected Travlr Getaways because it's one of the larger projects I completed and demonstrates experience with both frontend and backend development. For the software design and engineering enhancement, I combined the separate add-trip and edit-trip components into one reusable trip form. I also added trip deletion with a confirmation step, stronger form validation, loading and result messages, protected Angular routes, improved JWT protection, and centralized configuration values.

These changes demonstrate my skills in full-stack development, reusable Angular component design, authentication and authorization, protected API development, validation, error handling, testing, and code organization. The enhancement made the application more secure and maintainable while also adding functionality that was missing from the original version.

### Reflection

One thing that was reinforced to me when enhancing the artifact was how changing one part of an application can uncover problems in other areas that weren’t exactly obvious before. For instance, I found that the interface wasn’t updating correctly after logging in or out, and I also ran into an issue where changing a trip’s code caused the update request to fail because it was trying to use the new code instead of the original one. One of the bigger challenges was testing because my computer kept running out of memory when all of the tests ran at the same time. I ended up changing the test configuration so the files would run sequentially, which solved the problem.

This enhancement fully met Course Outcomes 4 and 5, which were the outcomes I planned to address in Module One. It met Outcome 4 because I used full-stack development tools, reusable components, centralized configuration, and testing to improve the application and add useful functionality. It met Outcome 5 because I strengthened authentication and authorization, protected the Angular pages and API endpoints, and prevented unauthorized access. I believe that both of my planned outcomes were met.

Overall, the enhancement improved the artifact by making it more secure, maintainable, reliable, and complete. I didn’t receive any instructor feedback that required revisions, so I didn’t need to make any additional changes before publishing the enhancement.

## View Original Travlr Source Code here
## View Enhanced Travlr Source Code here
