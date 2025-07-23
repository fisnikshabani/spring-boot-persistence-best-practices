# Spring Boot Persistence Best Practices

A comprehensive demonstration of JPA/Hibernate best practices using Spring Boot 3.5.3 and Java 21, focusing on entity associations and persistence patterns.

## Project Overview

This repository contains two Spring Boot applications that demonstrate different approaches to JPA entity associations:

- **associations-bidirectional**: Demonstrates bidirectional One-to-Many/Many-to-One relationships
- **associations-unidirectional**: Demonstrates unidirectional association patterns

## Technology Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Hibernate ORM**
- **MySQL 8.0**
- **Maven** for dependency management
- **Docker Compose** for database setup

## Key Features & Best Practices Demonstrated

### 1. Bidirectional Associations (`associations-bidirectional`)

#### Entity Design
- **Author Entity** (`Author.java:23-24`): Uses `@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)`
- **Book Entity** (`Book.java:21-23`): Uses `@ManyToOne(fetch = FetchType.LAZY)` with `@JoinColumn(name = "author_id")`

#### Best Practices Implemented
- **Lazy Loading**: Uses `FetchType.LAZY` to avoid N+1 query problems
- **Cascade Operations**: Properly configured cascade types for automatic persistence operations
- **Orphan Removal**: Ensures orphaned entities are automatically deleted
- **Bidirectional Sync**: Helper methods (`addBook`, `removeBook`, `removeBooks`) maintain consistency

#### Repository Pattern
- **Custom Queries** (`AuthorRepository.java:13-14`): Uses `JOIN FETCH` to optimize data retrieval
- **Transactional Operations**: Repository marked with `@Transactional` for proper transaction management

### 2. Performance Optimizations

- **Join Fetch Queries**: Prevents N+1 query problems by fetching related entities in single query
- **Proper equals() and hashCode()**: Implemented using entity ID for reliable collection operations
- **Lazy Initialization**: Default lazy loading strategy to improve performance

### 3. Database Configuration

- **Connection Pool**: Configured MySQL connection with proper JDBC settings
- **Hibernate Settings**: DDL auto-generation and SQL logging for development
- **Docker Integration**: Complete MySQL setup with persistent volumes

## Project Structure

```
spring-boot-persistence-best-practices/
├── associations-bidirectional/
│   ├── src/main/java/com/example/associations/
│   │   ├── entity/
│   │   │   ├── Author.java          # Author entity with bidirectional mapping
│   │   │   └── Book.java            # Book entity with proper equals/hashCode
│   │   └── repository/
│   │       ├── AuthorRepository.java # Custom queries with JOIN FETCH
│   │       └── BookRepository.java   # Standard JPA repository
│   ├── docker-compose.yml           # MySQL database setup
│   └── application.properties       # Database and JPA configuration
└── associations-unidirectional/
    └── src/main/java/...            # Unidirectional association examples
```

## Getting Started

### Prerequisites

- Java 21 or later
- Maven 3.6+
- Docker and Docker Compose

### Running the Bidirectional Associations Example

1. **Start the MySQL database:**
   ```bash
   cd associations-bidirectional
   docker compose up -d
   ```

2. **Build and run the application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Verify the setup:**
   - Application runs on `http://localhost:8080`
   - MySQL database available on `localhost:3306`
   - Database: `bookstoredb`
   - Username: `user`, Password: `password`

### Running the Unidirectional Associations Example

1. **Start the MySQL database:**
   ```bash
   cd associations-unidirectional
   docker compose up -d
   ```

2. **Build and run the application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Database Schema

The applications use the following database schema:

```sql
-- Authors table
CREATE TABLE Author (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    genre VARCHAR(255),
    age INT
);

-- Books table
CREATE TABLE Book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    isbn VARCHAR(255),
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES Author(id)
);
```

## Best Practices Highlighted

1. **Entity Relationship Management**
   - Proper use of `mappedBy` to avoid duplicate foreign keys
   - Correct cascade and orphan removal configuration
   - Bidirectional relationship synchronization methods

2. **Performance Optimization**
   - Lazy loading as default fetch strategy
   - JOIN FETCH queries to prevent N+1 problems
   - Proper transaction boundaries

3. **Code Quality**
   - Consistent equals() and hashCode() implementation
   - Serializable entities for distributed environments
   - Clean separation of concerns

4. **Development Best Practices**
   - Docker containerization for consistent development environment
   - Comprehensive logging and monitoring configuration
   - Maven project structure following Spring Boot conventions

## Contributing

This project serves as a reference implementation for Spring Boot persistence best practices. Feel free to explore the code and adapt the patterns to your specific use cases.

## License

This project is provided as educational material for demonstrating Spring Boot and JPA best practices.