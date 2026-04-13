# URL Shortener

A modern, production-ready URL shortening service built with Spring Boot 3 and Java 21. This REST API efficiently converts long URLs into short, shareable codes using Base62 encoding while providingfast redirection and comprehensive health monitoring.

## 🌟 Features

- ✅ **URL Shortening** - Convert long URLs to short, easy-to-share codes
- ✅ **URL Redirection** - Quick redirection from short codes to original URLs
- ✅ **Duplicate Detection** - Prevents duplicate URL storage for efficiency
- ✅ **Base62 Encoding** - Uses Base62 algorithm for URL encoding
- ✅ **URL Validation** - Validates HTTP/HTTPS URLs before shortening
- ✅ **Health Monitoring** - Built-in actuator endpoints for system health checks
- ✅ **REST API** - Clean, RESTful API design
- ✅ **Docker Support** - Ready for containerized deployment
- ✅ **Comprehensive Testing** - Unit and integration tests included

## 🛠 Technology Stack

| Component           | Version               |
|---------------------|-----------------------|
| Java                | 21 (LTS)              |
| Spring Boot         | 3.2.4                 |
| Spring Data JPA     | 3.2.4                 |
| H2 Database         | (Development/Testing) |
| MySQL               | 8.0+ (Production)     |
| Gradle              | 8.5+                  |
| Jakarta Persistence | Latest                |
| JUnit 5             | Latest                |

## 📋 Prerequisites

- **Java 21+** - [Download](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- **Gradle 8.5+** - [Download](https://gradle.org/releases/)
- **Git** - For cloning the repository
- **Docker & Docker Compose** (optional) - For containerized deployment

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/url-shortener.git
cd url-shortener
```

### 2. Build the Project

```bash
./gradlew clean build
```

### 3. Run the Application

```bash
# Using Gradle
./gradlew bootRun

# OR using compiled JAR
java -jar build/libs/url-shortener-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:3000**

### 4. Access the UI

Open your browser and navigate to:
```
http://localhost:3000
```

## 📡 API Endpoints

### 1. POST `/shorten` - Shorten a URL

Creates a shortened URL from the provided full URL.

**Request:**
```bash
curl -X POST http://localhost:3000/shorten \
  -H 'Content-Type: application/json' \
  -d '{"fullUrl":"https://example.com/very/long/url/path"}'
```

**Response (200 OK):**
```json
{
  "shortUrl": "http://localhost:3000/abc123xyz"
}
```

**Error Response (400 Bad Request):**
```json
{
  "field": "fullUrl",
  "value": "invalid-url",
  "message": "Invalid URL"
}
```

**Supported Protocols:** HTTP, HTTPS

---

### 2. GET `/{shortCode}` - Redirect to Original URL

Redirects to the original URL based on the shortened code.

**Example:**
```bash
curl -X GET http://localhost:3000/abc123xyz
```

**Response:** HTTP 302 Redirect to original URL

**Error Response (404 Not Found):**
- Returns 404 if the short code doesn't exist

---

### 3. GET `/actuator/health` - Health Check

System health check endpoint for monitoring.

**Request:**
```bash
curl http://localhost:3000/actuator/health
```

**Response:**
```json
{
  "status": "UP"
}
```

## 📁 Project Structure

```
url-shortener/
├── src/
│   ├── main/
│   │   ├── java/com/chandan/urlshortener/
│   │   │   ├── controller/        # REST endpoints
│   │   │   ├── service/           # Business logic
│   │   │   ├── repository/        # Data access layer
│   │   │   ├── model/             # JPA entities
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── common/            # Utility classes
│   │   │   ├── error/             # Error handling
│   │   │   └── UrlShortenerApplication.java
│   │   └── resources/
│   │       ├── static/            # UI files
│   │       └── application.properties
│   └── test/                      # Unit & integration tests
├── build.gradle
├── docker-compose.yml
├── api.Dockerfile
├── db.Dockerfile
└── schema.sql
```

## 🏗 Architecture

### Technology Components

1. **Controller Layer** - Handles HTTP requests/responses
2. **Service Layer** - Contains core business logic for URL shortening
3. **Repository Layer** - Data persistence using Spring Data JPA
4. **Model Layer** - JPA entities for database mapping

### URL Shortening Algorithm

- Uses **Base62 encoding** to convert database IDs to short codes
- Alphabet: `Mheo9PI2qNs5Zpf80TBn7lmRbtQ4YKXHvwAEWxuzdra316OJigGLSVUCyFjkDc`
- Duplicate URL detection prevents redundant entries

## 🧪 Testing

Run all tests:
```bash
./gradlew test
```

Test reports will be generated in:
```
build/reports/tests/test/index.html
```

### Test Coverage

- **Unit Tests** - URL utility and encoding functions
- **Integration Tests** - Controller and repository validation

## 🐳 Docker Deployment

### Build and Run with Docker Compose

```bash
docker-compose up --build
```

This will start:
- **API Server** on `http://localhost:8080`
- **MySQL Database** on port `3306`

### Docker Files

- **api.Dockerfile** - Java Spring Boot application container
- **db.Dockerfile** - MySQL database with schema initialization
- **docker-compose.yml** - Orchestration configuration

## 📊 Database

### Development (H2 In-Memory)
- Default configuration uses H2 in-memory database
- Data is lost on application restart
- Ideal for testing

### Production (MySQL)
To use MySQL for production:

1. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

2. Or use Docker Compose for automated setup

## 🔧 Configuration

Key application settings in `application.properties`:

```properties
server.port=3000
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
```


# Future Enhancements / Known Issues
* Since the project is for demo purpose only, Passwords are in plaintext. Will consider using Jasypt to encrypt the password in future
* Haven't implemented great looking Front-end application yet
* Faced issues with auto schema generation through JPA, so delegated the schema creation to Docker container
* Faced issues with api container not being able to get connection while mysql container was being set up, so added `?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false` to datasource url in application.properties. It slows down the application startup. You may remove that part if you want.
* Implement https
* Mount volumes for MySql container to persist data outside of the container
