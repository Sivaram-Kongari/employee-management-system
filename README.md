# 📚 Employee Management System (Spring Boot)

A RESTful backend application built using Spring Boot, JPA, MySQL for managing employees with features like CRUD operations, validation, pagination, sorting, and global exception handling.

## 🚀 Features

- Add new employees
- Update employee salary & department
- Delete employees
- View employee byId
- Pagination & sorting
- Input validation (Name, Email, Phone)
- Global exception handling
- DTO + ModelMapper implementation

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- REST API
- ModelMapper
- Maven

## Project Structure

com.springboot.employee
- ├── controller
- ├── service
- ├── repository
- ├── model
- ├── dto
- ├── exception
- └── config

## 📌 API Endpoints

- POST /api/employees/add
- PUT /api/employees/update/{id}
- DELETE /api/employees/delete/{id}
- GET /api/employees/getById/{id}

## Pagination & Sorting

- GET /api/employees?page=0&size=5&sortBy=name&direction=asc

## 🗄 Database

- employee

## ▶ How to Run

1. Clone the repo  
2. Create database: employee_db 
3. Update application.properties  
4. Run the application  


