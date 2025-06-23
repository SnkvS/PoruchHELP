# PoruchHelp

A Spring Boot application for user management with JWT authentication, built with Java 21 and PostgreSQL.

## 📋 Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Quick Start with Docker](#quick-start-with-docker)
- [Local Development Setup](#local-development-setup)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)

## ✨ Features

- **User Authentication**: JWT-based authentication system
- **RESTful API**: Clean REST endpoints for user management
- **Database Integration**: PostgreSQL with Liquibase migrations
- **Security**: Spring Security integration
- **API Documentation**: OpenAPI/Swagger documentation
- **Docker Support**: Full containerization with Docker Compose
- **Data Mapping**: MapStruct for efficient object mapping
- **Validation**: Bean validation with Spring Boot Starter Validation

## 🔧 Prerequisites

### For Docker (Recommended)
- **Docker**: Version 20.10 or higher
- **Docker Compose**: Version 2.0 or higher

### For Local Development
- **Java**: JDK 21 or higher
- **Maven**: Version 3.6 or higher
- **PostgreSQL**: Version 12 or higher
- **IDE (Optional)**: IntelliJ IDEA, Eclipse, or VS Code

## 🚀 Quick Start with Docker

The easiest way to run the application is using Docker Compose, which will set up both the application and PostgreSQL database.

### 1. Clone the Repository
```bash
git clone <repository-url>
cd PoruchHelp
```

### 2. Create Environment File
Create a `.env` file in the project root with the following variables:

```env
# Database Configuration
POSTGRES_USER=poruch
POSTGRES_PASSWORD=poruch
POSTGRES_DB=poruch
POSTGRES_LOCAL_PORT=5415
POSTGRES_DOCKER_PORT=5432

# Spring Boot Configuration
SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080
DEBUG_PORT=5005

# JWT Configuration
TOKEN_EXPIRATION=86400000
TOKEN_SECRET=your-secret-key-here-make-it-long-and-secure
```

### 3. Build and Run with Docker Compose
```bash
# Build the application JAR first
./mvnw clean package -DskipTests

# Start all services
docker-compose up --build
```

### 4. Access the Application
- **Application**: http://localhost:8080
- **API Documentation**: http://localhost:8080/swagger-ui/index.html 
- **Database**: localhost:5415 (from host machine)

### Docker Commands

```bash
# Start services in background
docker-compose up -d

# View logs
docker-compose logs -f app
docker-compose logs -f postgresdb

# Stop services
docker-compose down

# Rebuild and restart
docker-compose down
docker-compose up --build

# Remove volumes (⚠️ This will delete database data)
docker-compose down -v
```

## 🛠 Local Development Setup

### 1. Install Prerequisites
- Install Java 21: [Oracle JDK](https://www.oracle.com/java) or [OpenJDK](https://openjdk.org)
- Install Maven: [Apache Maven](https://maven.apache.org)
- Install PostgreSQL: [PostgreSQL Download](https://www.postgresql.org/download)

### 2. Database Setup
```sql
-- Connect to PostgreSQL and create database
CREATE DATABASE poruch;
CREATE USER poruch WITH PASSWORD 'poruch';
GRANT ALL PRIVILEGES ON DATABASE poruch TO poruch;
```

### 3. Configure Application
Update `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5415/poruch
spring.datasource.username=poruch
spring.datasource.password=poruch
```

## 🔨 Building the Project

### Maven Build
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package (creates JAR file)
./mvnw clean package

# Skip tests during packaging
./mvnw clean package -DskipTests
```

### Docker Build
```bash
# Build Docker image manually
docker build -t poruch_help:latest .

# Build with specific tag
docker build -t poruch_help:v1.0.0 .
```

## ▶️ Running the Application

### Option 1: Docker Compose (Recommended)
```bash
docker-compose up
```

### Option 2: Local with Maven
```bash
# Ensure PostgreSQL is running locally
./mvnw spring-boot:run
```

### Option 3: JAR File
```bash
# After building with Maven
java -jar target/PoruchHelp-0.0.1-SNAPSHOT.jar
```

### Option 4: IDE
- Import the project into your IDE
- Run the `PoruchHelpApplication` main class
- Ensure PostgreSQL is running and accessible

## 📚 API Documentation

Once the application is running, you can access the API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

### Key Endpoints

- **Authentication**:
  - `POST /api/auth/login` - User login
  - `POST /api/auth/register` - User registration
  
- **Test Endpoints**:
  - `GET /api/test` - Test endpoint

## 🗄 Database Schema

The application uses PostgreSQL with Liquibase for database migrations. The schema includes:

- **Users Table**: User management with fields like `id`, `email`, `password`, etc.
- **Soft Deletion**: Support for `is_deleted` flag
- **Audit Fields**: Created/updated timestamps

### Database Access
- **Host**: localhost
- **Port**: 5415 (when using Docker Compose)
- **Database**: poruch
- **Username**: poruch
- **Password**: poruch

You can connect using tools like:
- pgAdmin
- DBeaver
- psql command line

## ⚙️ Configuration

### Environment Variables
The application supports the following environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| `POSTGRES_USER` | Database username | poruch |
| `POSTGRES_PASSWORD` | Database password | poruch |
| `POSTGRES_DB` | Database name | poruch |
| `TOKEN_EXPIRATION` | JWT token expiration (ms) | 86400000 |
| `TOKEN_SECRET` | JWT secret key | - |

### Profiles
- **Default**: Uses `application.properties`
- **Development**: Uses `application-dev.properties`
- **Production**: Configured via Docker environment

### Application Properties
Key configuration options in `application.properties`:

```properties
# Application
spring.application.name=PoruchHelp

# Database
spring.datasource.url=jdbc:postgresql://localhost:5415/poruch
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# JPA
spring.jpa.open-in-view=false
```

## 🔍 Troubleshooting

### Common Issues

#### Docker Issues
```bash
# Check if containers are running
docker-compose ps

# Check container logs
docker-compose logs app
docker-compose logs postgresdb

# Restart services
docker-compose restart

# Clean rebuild
docker-compose down
docker system prune -f
docker-compose up --build
```

#### Database Connection Issues
- Verify PostgreSQL is running: `docker-compose ps`
- Check database logs: `docker-compose logs postgresdb`
- Verify environment variables in `.env` file
- Ensure ports are not conflicting

#### Build Issues
```bash
# Clean Maven cache
./mvnw clean

# Check dependency tree
./mvnw dependency:tree

# Verify Java version
java -version
./mvnw -version
```

#### Port Conflicts
- Check if port 8080 is in use: `lsof -i :8080`
- Check if port 5415 is in use: `lsof -i :5415`
- Modify ports in `.env` file if needed

### Health Checks
- **Application Health**: http://localhost:8080/actuator/health (if actuator is enabled)
- **Database Connection**: Check application logs for connection errors

### Logs
- **Application Logs**: `docker-compose logs -f app`
- **Database Logs**: `docker-compose logs -f postgresdb`
- **All Logs**: `docker-compose logs -f`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests: `./mvnw test`
5. Submit a pull request

**Note**: This README assumes standard Spring Boot conventions. Adjust configurations based on your specific requirements and environment setup.
