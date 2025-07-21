# Multiple Data Sources with Spring Boot 3 and Java 21

A comprehensive Spring Boot 3 application demonstrating how to configure and use multiple data sources (MySQL, PostgreSQL, and Oracle) with JPA in a single application.

📘 Blog Post: [Multiple Data Sources in Spring Boot 3 with Java 21](https://jarmx.blogspot.com/2023/12/multiple-data-sources-in-spring-boot-3.html)

## 🚀 Features

- **Multi-Database Support**: Configure MySQL, PostgreSQL, and Oracle databases simultaneously
- **Spring Boot 3.2.0**: Latest Spring Boot features and improvements
- **Java 21**: Modern Java with record classes and preview features
- **Docker Compose**: Easy database setup for development
- **RESTful APIs**: CRUD operations for each data source
- **Comprehensive Testing**: Unit tests with Mockito
- **Clean Architecture**: Separate configurations for each data source

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 21 (Zulu)
- **Build Tool**: Maven
- **Databases**: 
  - MySQL 8.0
  - PostgreSQL 14.1
  - Oracle XE 11g
- **Containerization**: Docker Compose
- **Testing**: JUnit 5 + Mockito
- **Additional Libraries**: Lombok, Guava

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- Java 21 or higher
- Maven 3.6 or higher
- Docker and Docker Compose
- Git

## 🏗️ Project Structure

```
multiple-data-sources-jpa/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── henry/
│   │               ├── configuration/     # Database configurations
│   │               ├── controller/        # REST controllers
│   │               ├── model/            # Entity classes
│   │               │   ├── user/         # MySQL entities
│   │               │   ├── company/      # PostgreSQL entities
│   │               │   └── brand/        # Oracle entities
│   │               ├── repository/       # JPA repositories
│   │               ├── service/          # Business logic
│   │               └── record/           # Configuration records
│   └── test/
│       └── java/
│           └── com/
│               └── henry/                # Unit tests
├── docker-compose-multiple-db.yml       # Database containers
├── pom.xml                              # Maven dependencies
└── README.md
```

## ⚙️ Configuration

### Database Configuration

The application uses separate configurations for each database:

| Database   | Entity Package            | Repository Package            | Port | Database Name |
|------------|--------------------------|-------------------------------|------|---------------|
| MySQL      | `com.henry.model.user`   | `com.henry.repository.user`   | 3306 | test_db       |
| PostgreSQL | `com.henry.model.company`| `com.henry.repository.company`| 5432 | postgre_test  |
| Oracle     | `com.henry.model.brand`  | `com.henry.repository.brand`  | 1521 | xe            |

### Application Properties

```yaml
server:
  port: 9000
  servlet:
    context-path: /

spring:
  datasource:
    mysql:
      url: jdbc:mysql://localhost:3306/test_db?allowPublicKeyRetrieval=true
      username: test
      password: test_pass
      driverClassName: com.mysql.cj.jdbc.Driver
      ddlAuto: create-drop
    postgres:
      url: jdbc:postgresql:postgre_test
      username: postgre_test
      password: postgre_test
      driverClassName: org.postgresql.Driver
      ddlAuto: create-drop
    oracle:
      url: jdbc:oracle:thin:@//localhost:1521/xe
      username: system
      password: oracle
      driverClassName: oracle.jdbc.OracleDriver
      ddlAuto: update
```

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/HenryXiloj/multiple-data-sources-jpa.git
cd multiple-data-sources-jpa
```

### 2. Start Database Containers

```bash
docker-compose -f docker-compose-multiple-db.yml up -d
```

This command will start:
- **MySQL** on port 3306
- **PostgreSQL** on port 5432  
- **Oracle XE** on port 1521

### 3. Install Oracle JDBC Driver (if needed)

```bash
mvn install:install-file \
  "-Dfile=path/to/your/ojdbc6.jar" \
  "-DgroupId=com.oracle" \
  "-DartifactId=ojdbc6" \
  "-Dversion=11.2.0.4" \
  "-Dpackaging=jar" \
  "-DgeneratePom=true"
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:9000`

## 📚 API Endpoints

### User Management (MySQL)
- **POST** `/api/v1/users` - Create a new user

### Company Management (PostgreSQL)  
- **POST** `/api/v2/companies` - Create a new company

### Brand Management (Oracle)
- **POST** `/api/v3/brands` - Create a new brand

### Example Requests

#### Create User (MySQL)
```bash
curl -X POST http://localhost:9000/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John",
    "lastName": "Doe"
  }'
```

#### Create Company (PostgreSQL)
```bash
curl -X POST http://localhost:9000/api/v2/companies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Corp"
  }'
```

#### Create Brand (Oracle)
```bash
curl -X POST http://localhost:9000/api/v3/brands \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Premium Brand"
  }'
```

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Classes
```bash
# Test User Service (MySQL)
mvn test -Dtest=UserServiceTest

# Test Brand Service (Oracle)  
mvn test -Dtest=BrandServiceTest

# Test Company Service (PostgreSQL)
mvn test -Dtest=CompanyServiceTest
```

## 🔧 Database Management

### Check Container Status
```bash
docker-compose -f docker-compose-multiple-db.yml ps
```

### Stop Databases
```bash
docker-compose -f docker-compose-multiple-db.yml down
```

### Remove Containers and Volumes
```bash
docker-compose -f docker-compose-multiple-db.yml down -v
```

## 🏛️ Architecture Details

### Configuration Classes
- **MysqlConfig**: Configures MySQL data source with primary annotations
- **PostgreConfig**: Configures PostgreSQL data source  
- **OracleConfig**: Configures Oracle data source

### Key Components
- **Record Classes**: Type-safe configuration properties using Java 21 records
- **Sealed Interfaces**: Restrict service implementations for better type safety
- **Repository Pattern**: Standard Spring Data JPA repositories
- **Service Layer**: Business logic abstraction with common interface

## 🔍 Troubleshooting

### Common Issues

1. **Lombok Plugin**: Ensure Lombok plugin is installed in your IDE
2. **Cache Issues**: Try `File | Invalidate Caches` in IntelliJ IDEA
3. **Port Conflicts**: Change database ports if you have local databases running
4. **Oracle Driver**: Manually install Oracle JDBC driver if not found

### Database Connection Issues

- **MySQL**: Check if port 3306 is available
- **PostgreSQL**: Verify port 5432 is not in use
- **Oracle**: Ensure port 1521 is accessible and container is running

### IDE Configuration

For IntelliJ IDEA:
- Enable annotation processing for Lombok
- Set Project SDK to Java 21
- Enable preview features in compiler settings

## 📖 Detailed Documentation

For comprehensive implementation details and step-by-step explanations, visit:
[Multiple Data Sources in Spring Boot 3 with Java 21](https://jarmx.blogspot.com/2023/12/multiple-data-sources-in-spring-boot-3.html)

## 📦 Dependencies

Key dependencies used in this project:

- `spring-boot-starter-web` - Web application support
- `spring-boot-starter-data-jpa` - JPA support
- `spring-boot-starter-jetty` - Jetty web server
- `mysql-connector-java` - MySQL driver
- `postgresql` - PostgreSQL driver
- `ojdbc6` - Oracle driver
- `lombok` - Boilerplate code reduction
- `guava` - Google's core Java libraries

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Docker community for containerization tools
- Oracle, PostgreSQL, and MySQL teams for robust database systems
