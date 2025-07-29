# LinkedIn Backend Clone – Microservices Architecture (Spring Boot)

This project is a scalable backend clone of **LinkedIn**, built using **Spring Boot** and **Microservices architecture**. The system is designed to support key social features like user registration, post creation, connections, and real-time notifications.

---

## 📌 Architecture Overview

The application uses:
- **Spring Cloud Gateway** as the API Gateway
- **Eureka** for Service Discovery
- **Spring Cloud Config Server** for centralized configuration
- **Kafka** for asynchronous event processing
- **Zipkin** and **ELK stack** for observability
- **Resilience4j** for circuit breaking

---

## 🧱 Microservices

Each service runs independently and communicates via REST and Kafka:

| Service              | Description                               |
|----------------------|-------------------------------------------|
| `User Service`       | Handles user authentication and profile   |
| `Post Service`       | Manages posts and likes                   |
| `Connections Service`| Manages user connections and degrees      |
| `Notification Service` | Sends real-time and batch notifications |
| `Config Server`      | Centralized configuration using Git       |
| `Registry Service`   | Service discovery with Eureka             |
| `API Gateway`        | Entry point for all client requests       |

---

## 🧪 REST APIs

### 👤 User Service
- `POST /auth/signup` — Register new user
- `POST /auth/login` — Authenticate user
- `GET /users/{userId}/profile` — Get profile
- `PUT /users/{userId}/profile` — Update profile

### 📝 Post Service
- `POST /posts` — Create a post
- `GET /posts/{postId}` — Get post
- `POST /posts/{postId}/like` — Like a post
- `GET /users/{userId}/posts` — Get user’s posts

### 🤝 Connections Service
- `POST /connections/request` — Send connection request
- `POST /connections/accept` — Accept request
- `GET /connections/{userId}/{degree}` — Get user connections

### 🔔 Notification Service
- `GET /notifications/{userId}` — Get all notifications
- `POST /notifications/mark-as-read` — Mark notifications as read

---

## 🔁 Inter-service Communication

Inter-service calls are handled via:
- **Feign Clients** for synchronous REST calls
- **Kafka Topics** for asynchronous event-driven communication (e.g., post like → trigger notification)

---

## ⚙️ Tech Stack

- **Language**: Java 17+
- **Framework**: Spring Boot, Spring Cloud
- **API Gateway**: Spring Cloud Gateway
- **Service Registry**: Eureka
- **Config Management**: Spring Cloud Config
- **Messaging**: Apache Kafka
- **Resilience**: Resilience4j (Circuit Breakers)
- **Authentication**: JWT Tokens

---

## 🧪 Running Locally

### Prerequisites
- Java 17
- Kafka + Zookeeper
- Spring Boot CLI (or IDE like IntelliJ)
