# Todo Application Spring Boot

## Project Description
This Spring Boot application serves as a backend for managing tasks. Users can create, update, and delete tasks after registering or logging in. Authentication is enforced through the use of an access token, which must be included in the Authorization header for creating, updating, or deleting tasks.

## Table Of Content
- ### [Features](#features)
- ### [Technologies Used](#technologies-used)
- ### [Getting Started](#getting-started)
- ### [Architecture](#architecture)
- ### [Usage](#usage)
- ### [Installation](#installation)
- ### [Postman Collection](#postman-collection)

## Features
- User Registration: New users can register to access the task operations (Create , Update , Delete).
- User Authentication: Existing users can log in using their credentials.
- Task Operations: Authenticated users can create, update, and delete tasks.
- Access Token: Users must include a valid authentication access token in the Authorization header for secure task operations.



## Technologies Used
- Java 17
- Spring Boot
- Spring AOP (Aspect Oriented Programming) for Logging
- Spring Data Jpa
- Spring Security Token Based Authentication
- MySQL Database
- Swagger Documentation



## Getting Started
- Clone the repository.
- Configure database settings.
- Build and run the Spring Boot application.
- Register as a new user or log in with existing credentials.
- Obtain an access token and include it in the Authorization header for task operations.


## Architecture
![Todo-App-Spring-Boot drawio (1)](https://github.com/youssefGamalMohamed/todo-app-spring-boot/assets/47324621/10502122-1c6e-4647-807b-e8adc2c8e8c3)



## Usage
- Register a new user: ``` POST /api/v1/todo/auth/register ```
- Log in: ``` POST /api/v1/todo/auth/login ```
- Create a task: ``` POST /api/v1/todo/task ```
- Update a task: ``` PUT /api/v1/todo/task/{taskId} ```
- Delete a task: ``` DELETE /api/v1/todo/task/{taskId} ```
- Retrieve All Task With Specific Status as Query Parameter: 
  - for completed tasks: ``` GET /api/v1/todo/task?status=COMPLETED ```
  - for in-completed tasks: ``` GET /api/v1/todo/task?status=IN_COMPLETED ```
- Add Existing Task for User: ``` PUT /api/v1/todo/task/{taskId}/user/{userId} ```


## Installation
To install the project, follow these steps:

- Open MySQL Workbench

- Create new schema called ```todo```

- Clone the repository to your local machine.
  ```bash
    https://github.com/youssefGamalMohamed/todo-app-spring-boot.git
  ```

- Navigate to the project directory.
  ```bash
    cd todo-app-spring-boot
  ```
- Open you favourite IDE for the project
- Build ```home``` Profile
- Run Project

## Postman Collection
- Download the Postman Collection from
