# RestfulGym

## Project Description

RestfulGym is a RESTful application designed to manage schedules and users of a gym. The system uses WebSockets to
notify users of any schedule changes. The project is built using **Spring Boot**, **PostgreSQL**, **Swagger**, and *
*Docker Compose**.

## Tech Stack

- **Spring Boot** — main framework for the application
- **PostgreSQL** — database for storing user and schedule data
- **Google Jib** — for building Docker images
- **Docker Compose** — for managing containers (application and database)
- **Swagger** — for API documentation and testing
- **WebSocket** — for real-time schedule change notifications

## Quick Start

### Requirements

- Docker and Docker Compose
- JDK 17+

### Installation and Running

1. **Clone the repository**:

   ```bash
   git clone https://github.com/MikalaiSamtsevich/RestfulGym.git
   cd RestfulGym
   ```

2. **Build the Docker image with Jib**:

   Run the following command to build the image:

   ```bash
   ./mvnw jib:dockerBuild
   ```

3. **Run the containers with Docker Compose**:

   To start the application and the database, run:

   ```bash
   docker-compose up -d
   ```

   This will launch two containers: one for the Spring Boot application and one for the PostgreSQL database.

4. **Swagger API documentation**:

   After the application starts, you can access the Swagger UI at:

   ```
   http://localhost:8080/swagger-ui.html
   ```

5. **Admin login credentials**:

    - **Username**: `charlie`
    - **Password**: `user`

6. **WebSocket notifications**:

   WebSocket notifications are available at:

   ```
   ws://localhost:8080/notifications
   ```

## Developer

- **Name**: Nikolay
- **Contact**: [mikalai.samtsevich@gmail.com](mailto:mikalai.samtsevich@gmail.com)
