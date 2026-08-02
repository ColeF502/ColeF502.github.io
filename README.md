# ColeF502.github.io

## SNHU CS 499 2-2 Milestone One Code Review

### This code review examines the original versions of my Travlr Getaways full-stack web application and Contact, Task, and Appointment Services artifacts. It identifies areas for improvement and explains my planned enhancements in software design and engineering, algorithms and data structures, and databases.

## [Watch my Code Review here](https://youtu.be/OL9BIUAW2qg)

## Software Design and Engineering

### Travlr Getaways

### Artifact Overview

Travlr Getaways is a full-stack travel application that I originally created for CS 465: Full Stack Development I in June 2026. The project includes a customer-facing travel website, an Angular administrative application, a Node.js and Express backend, and a MongoDB database used to store trip information. Authenticated administrators can use the administrative side to manage the available trip records.

### Enhancement and Skills Demonstrated

I selected Travlr Getaways because it is one of the larger projects I completed and demonstrates experience with both frontend and backend development. For the software design and engineering enhancement, I combined the separate add-trip and edit-trip components into one reusable trip form. I also added trip deletion with a confirmation step, stronger form validation, loading and result messages, protected Angular routes, improved JWT protection, and centralized configuration values.

These changes demonstrate my skills in full-stack development, reusable Angular component design, authentication and authorization, protected API development, validation, error handling, testing, and code organization. The enhancement made the application more secure and maintainable while also adding functionality that was missing from the original version.

### Course Outcomes

This enhancement met the two course outcomes I planned to address in Module One. The reusable trip form, testing, centralized configuration, and improvements to the application’s structure demonstrate Course Outcome 4. The authentication improvements, route guards, JWT verification, and protected API endpoints demonstrate Course Outcome 5.

I did not need to update my original outcome-coverage plan because the finished enhancement addressed the outcomes and skills I intended it to cover.

### Reflection

Enhancing this project reinforced how changing one part of an application can uncover problems in other areas that were not obvious at first. For example, the interface did not update correctly after logging in or out, and changing a trip’s code caused an update request to fail because the application tried to use the new code instead of the original one.

Testing was another challenge because my computer ran out of memory when all the Angular tests ran at the same time. I changed the test configuration so the files ran sequentially, which resolved the problem. Working through these issues showed me the importance of testing changes carefully and considering how the frontend, backend, authentication system, and API interact with each other.

### Instructor Feedback

I did not receive any instructor feedback requiring revisions to this enhancement. After receiving full credit, I reviewed the artifact again and prepared the graded version for publication in my ePortfolio.

## View Original Travlr Source Code here
## View Enhanced Travlr Source Code here
