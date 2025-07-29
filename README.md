# LinkedIn Backend Clone – Microservices Architecture (Spring Boot)

This project is a scalable backend clone of **LinkedIn**, built using **Spring Boot** and **Microservices architecture**. The system is designed to support key social features like user registration, post creation, connections, and real-time notifications.

---

## 📌 Architecture Overview

![Architecture Diagram](./bb047768-592e-4de1-af4f-d4e80733e072.png)

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

![API Overview](./12cb4b22-86ee-4e77-954b-a204333e795e.png)

---

## 🧾 Domain Model

The system includes clearly defined domains with normalized schema:

![Database Schema](./60f6eb8b-3125-4b9e-b166-51181b071797.png)

---

## 🔁 Inter-service Communication

Inter-service calls are handled via:
- **Feign Clients** for synchronous REST calls
- **Kafka Topics** for asynchronous event-driven communication (e.g., post like → trigger notification)

![Inter-service Flow](./d4f6e173-bbad-4b54-b696-016409d532a8.png)

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
