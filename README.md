# Employee Performance Tracker API

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Lombok

## Features
- Employee management
- Performance review system
- Review cycle tracking
- Goal tracking
- Cycle analytics (average rating, top performer)

## API Endpoints

### Employee
POST /employees  
GET /employees/{id}/reviews  
GET /employees?department=&minRating=

### Review
POST /reviews  

### Review Cycle
POST /cycles  
GET /cycles  
GET /cycles/{id}/summary  

## How to Run

1. Create PostgreSQL DB:
   CREATE DATABASE employee_performance_tracker;

2. Update application.properties

3. Run:
   mvn spring-boot:run

## Author
Abhijeet Kumar
