# Java Student Management System (using JDBC)

This project is a command-line application that demonstrates how to connect a Java application to a MySQL database using pure **JDBC (Java Database Connectivity)**. It provides full CRUD (Create, Read, Update, Delete) functionality for managing student records and also includes several advanced data aggregation queries.

This project was built to master the fundamentals of Java's database connection API before upgrading to a modern ORM.
**A more advanced version of this same project, built with Hibernate, can be found here:** [ https://github.com/Revathireddyb7/MyHibernateProject.git ]

## ✨ Features

* **Full CRUD Functionality:**
    * **Create:** Add a new student to the database.
    * **Read:** Retrieve and display a list of all students or a single student by their ID.
    * **Update:** Modify the information of an existing student.
    * **Delete:** Remove a student from the database.
* **Advanced Data Aggregation:**
    * Calculate the average age of all students.
    * Count the total number of students in each branch (`GROUP BY`).
    * Find the oldest student in the database (`MIN()`).
    * Find the youngest student in the database (`MAX()`).
* **Clean, Layered Code:** Follows a logical structure by separating database logic, student models, and the main application logic.

## 💻 Technologies Used

* **Java** 
* **JDBC (Java Database Connectivity) API**
* **PostgreSQL**



