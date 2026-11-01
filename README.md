# Job Portal Application

A **Job Portal REST API** developed using Java and Spring Boot that connects job seekers with companies. The application enables candidates to manage their profiles, search for job opportunities, apply for jobs, and track their applications. Companies can post jobs and manage applications.

## 🚀 Project Overview

The Job Portal is a backend application designed to simplify the recruitment process by providing RESTful APIs for candidates and companies.

## 🛠️ Technologies Used

* **Programming Language:** Java
* **Backend Framework:** Spring Boot
* **Database:** PostgreSQL
* **ORM:** Hibernate / JPA
* **Build Tool:** Maven
* **API Testing:** Postman
* **IDE:** Eclipse
* **Version Control:** Git and GitHub

## ✨ Features

### Candidate Management

* Register candidates.
* Manage candidate profiles.
* Maintain education, experience, and skills.
* Search and view available jobs.
* Apply for jobs.
* View application history.

### Company Management

* Register companies.
* Create and manage job postings.
* Update job details and application deadlines.
* View jobs posted by a company.
* Manage application statuses.
* Delete job postings when permitted.

### Job Management

* Create and update job postings.
* Search jobs by role.
* Manage active and inactive job postings.
* Maintain job requirements, salary, and application deadlines.

### Application Management

* Submit job applications.
* Track application statuses.
* Maintain candidate application history.
* Manage applications through company operations.

## 🏗️ Project Architecture

The application follows a layered architecture:

* **Controller Layer:** Handles HTTP requests and API responses.
* **Service Layer:** Implements business logic.
* **Repository Layer:** Performs database operations using Spring Data JPA.
* **Entity Layer:** Represents database tables.
* **DTO Layer:** Transfers data between the client and application.
* **Exception Layer:** Handles application errors and exceptions.

## 📋 Prerequisites

Install the following before running the application:

1. Java Development Kit (JDK)
2. PostgreSQL
3. Maven
4. Eclipse IDE or another Java IDE
5. Postman

## ⚙️ Configuration

Create a PostgreSQL database named `jobportal`.

Configure the database connection in `src/main/resources/application.properties`:

```properties
spring.application.name=jobportal
server.port=8083

spring.datasource.url=jdbc:postgresql://localhost:5432/jobportal
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables to your local PostgreSQL credentials.

**Security note:** Never commit real database passwords, access tokens, or other secrets to GitHub.

## ▶️ How to Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/ravikirankotika-rk/Job-Portal-Spring-Boot.git
   ```

2. Open the project in Eclipse IDE.

3. Configure the PostgreSQL database and connection properties.

4. Allow Maven to download the required dependencies.

5. Run the Spring Boot main application class.

6. The application will start at:

   ```text
   http://localhost:8083
   ```

7. Use Postman to test the REST APIs.

## 🧪 API Testing

The REST APIs can be tested using Postman.

Example local base URL:

```text
http://localhost:8083
```

Use the appropriate controller endpoint, HTTP method, request headers, and JSON request body when testing each API.

## 🎯 Learning Outcomes

Through this project, I gained practical experience with:

* Java programming and object-oriented principles.
* Spring Boot application development.
* REST API design and implementation.
* Hibernate and Spring Data JPA.
* PostgreSQL database integration.
* Entity relationships and CRUD operations.
* DTOs and layered architecture.
* Exception handling.
* API testing using Postman.
* Git and GitHub version control.

## 🔮 Future Enhancements

* User authentication and authorization using Spring Security.
* Role-based access for candidates and companies.
* Email notifications for application updates.
* Frontend integration.
* Advanced job search and filtering.
* Deployment to a cloud platform.

## 👨‍💻 Author

**Ravi Kiran Kotika**

Java Full Stack Developer | Spring Boot | REST APIs | PostgreSQL

GitHub: https://github.com/ravikirankotika-rk

---

*This project was developed for learning and demonstrating backend application development using Java and Spring Boot.*
