# Employee Management System

## Overview
The Employee Management System is a Java-based web application built using Spring Boot. It provides full CRUD (Create, Read, Update, Delete) operations for managing employees, departments, and projects. The application incorporates OAuth2.0 and OpenID Connect (OIDC) for secure authentication and authorization using the Authorization Code Flow. It interacts with a relational database (MySQL or H2) for data storage and manipulation, implements caching strategies, and provides comprehensive unit testing for all components.

## Features
- CRUD operations for Employees, Departments, and Projects
- Advanced database interaction using Spring Data JPA
- OAuth2.0 and OpenID Connect integration for secure authentication and authorization
- Role-based access control (ADMIN, MANAGER, EMPLOYEE)
- RESTful API endpoints
- Caching implementation using Spring Cache
- Data validation and error handling
- Comprehensive unit testing with JUnit and Mockito

## Technologies Used
- Spring Boot 3.4.2
- Spring Data JPA
- Spring Security
- OAuth2 Client
- MySQL or H2 Database
- Spring Cache
- JUnit and Mockito for unit testing

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven or Gradle
- MySQL or H2 Database

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/employee-management-system.git
   cd employee-management-system
Configure the database in src/main/resources/application.properties:

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

# OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-client-secret
spring.security.oauth2.client.registration.google.scope=openid, profile, email
spring.security.oauth2.client.registration.google.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.client.provider.google.user-name-attribute=sub

# Cache Configuration
spring.cache.type=simple

# Logging Configuration
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=DEBUG
Build the project:

mvn clean install
Run the application:

mvn spring-boot:run
API Endpoints
/employees - Accessible by ADMIN and MANAGER
/employees/{id} - Accessible by ADMIN, MANAGER (for employees in their department), and the EMPLOYEE themselves
/departments - Accessible by ADMIN and MANAGER
/projects - Accessible by ADMIN and MANAGER
/employees/search - Search employees by various criteria (accessible by ADMIN and MANAGER)
/departments/{id}/projects - List all projects under a specific department (accessible by ADMIN and MANAGER)
Unit Testing
The project includes comprehensive unit tests for controllers, services, and repositories using JUnit and Mockito. To run the tests, use the following command:

mvn test
Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.
