
# Todo Application Spring Boot

## Project Description
This Spring Boot application serves as a backend for managing tasks. Users can create, update, and delete tasks after registering or logging in. Authentication is enforced through the use of an access token, which must be included in the Authorization header for creating, updating, or deleting tasks.

## Table Of Contents
- ### [Features](#features)
- ### [Technologies Used](#technologies-used)
- ### [Getting Started](#getting-started)
- ### [Architecture](#architecture)
- ### [Usage](#usage)
- ### [Installation](#installation)
- ### [Running the Application with Docker Compose](#running-the-application-with-docker-compose)
- ### [Postman Collection](#postman-collection)

## Features
- User Registration: New users can register to access the task operations (Create, Update, Delete).
- User Authentication: Existing users can log in using their credentials.
- Task Operations: Authenticated users can create, update, and delete tasks.
- Access Token: Users must include a valid authentication access token in the Authorization header for secure task operations.

## Technologies Used
- Java 17
- Spring Boot
- Spring AOP (Aspect Oriented Programming) for Logging
- Spring Data JPA
- Spring Security Token-Based Authentication
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
- Register a new user: `POST /api/v1/todo/auth/register`
- Log in: `POST /api/v1/todo/auth/login`
- Create a task: `POST /api/v1/todo/task`
- Update a task: `PUT /api/v1/todo/task/{taskId}`
- Delete a task: `DELETE /api/v1/todo/task/{taskId}`
- Retrieve All Tasks With Specific Status as Query Parameter:
  - For completed tasks: `GET /api/v1/todo/task?status=COMPLETED`
  - For in-completed tasks: `GET /api/v1/todo/task?status=IN_COMPLETED`
- Add Existing Task for User: `PUT /api/v1/todo/task/{taskId}/user/{userId}`

## Installation
To install the project, follow these steps:

- Open MySQL Workbench
- Create a new schema called `todo`
- Clone the repository to your local machine:
  ```bash
  git clone https://github.com/youssefGamalMohamed/todo-app-spring-boot.git
  ```
- Navigate to the project directory:
  ```bash
  cd todo-app-spring-boot
  ```
- Open your favorite IDE for the project
- Build the `home` profile
- Run the project

## Running the Application with Docker Compose

To simplify the setup and execution of the application, you can use Docker Compose. Follow these steps:

1. **Ensure Docker and Docker Compose are installed**: Make sure Docker and Docker Compose are installed on your machine. You can download them from the [Docker website](https://www.docker.com/products/docker-desktop) if you haven't already.

2. **Clone the repository**: If you haven't cloned the repository yet, do so with the following command:
    ```bash
    git clone https://github.com/youssefGamalMohamed/todo-app-spring-boot.git
    ```
    Navigate to the project directory:
    ```bash
    cd todo-app-spring-boot
    ```

3. **Configure Docker Compose**: Ensure that your `docker-compose.yml` file is correctly configured. An example `docker-compose.yml` might look like this:

    ```yaml
    version: '3.8'
    services:
      mysql:
        image: mysql:5.7
        container_name: mysql
        environment:
          MYSQL_ROOT_PASSWORD: root
          MYSQL_DATABASE: todo
          MYSQL_USER: user
          MYSQL_PASSWORD: password
        ports:
          - "3306:3306"
        networks:
          - todo-network

      app:
        image: openjdk:17-jdk
        container_name: todo-app
        depends_on:
          - mysql
        volumes:
          - .:/app
        working_dir: /app
        command: ./mvnw spring-boot:run
        ports:
          - "8080:8080"
        networks:
          - todo-network

    networks:
      todo-network:
        driver: bridge
    ```

4. **Build and run the containers**: Use Docker Compose to build and run the application:
    ```bash
    docker-compose up --build
    ```

5. **Access the application**: Once the containers are up and running, you can access the Spring Boot application at `http://localhost:8080`. The MySQL database will be accessible on port `3306`.

6. **Stopping the containers**: To stop the running containers, use:
    ```bash
    docker-compose down
    ```

This setup will help you run the application in a containerized environment, simplifying development and deployment.

## Postman Collection
- Download the Postman Collection from [here](https://github.com/youssefGamalMohamed/todo-app-spring-boot/blob/f7b9e0326a185a1fc30291458220ecd4eeaaa13f/README.md)

