# Associations Application with MySQL Database

This README provides instructions for setting up and running the Associations application with a MySQL database using Docker Compose.

## Prerequisites

- Docker and Docker Compose installed on your system
- Java 21 or later
- Maven

## Setup Instructions

### 1. Start the MySQL Database

Navigate to the project directory and start the MySQL database using Docker Compose:

```bash
cd /path/to/associations
docker compose up -d
```

This will start a MySQL 8.0 database with the following configuration:
- Database name: associations
- Username: user
- Password: password
- Port: 3306 (mapped to host)

### 2. Build and Run the Application

Once the MySQL database is running, you can build and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

The application will connect to the MySQL database using the configuration in `application.properties`.

## Configuration Details

### Docker Compose Configuration

The `docker-compose.yml` file defines a MySQL service with:
- MySQL 8.0 image
- Persistent volume for data storage
- Environment variables for database name, user, and passwords
- Port mapping (3306:3306)
- Health check to ensure the database is running properly

### Database Connection Configuration

The database connection is configured in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/associations
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

## Stopping the Database

To stop the MySQL database:

```bash
docker compose down
```

To stop and remove the volume (this will delete all data):

```bash
docker compose down -v
```

## Troubleshooting

If you encounter issues connecting to the database, ensure:
1. The MySQL container is running: `docker ps`
2. The database is accessible: `mysql -h localhost -P 3306 -u user -p`
3. The application.properties file has the correct connection details