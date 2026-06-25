# 📝 Blog API

A secure and scalable REST API built with Spring Boot 3 for blog management. This project demonstrates JWT-based authentication, authorization, CRUD operations, pagination, sorting, search functionality, validation, exception handling, and API documentation using Swagger/OpenAPI.

[![Java](https://img.shields.io/badge/Java-21-orange)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)]()
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)]()
[![JWT](https://img.shields.io/badge/JWT-Authentication-yellow)]()
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-green)]()
[![License](https://img.shields.io/badge/License-MIT-purple)]()

---

## 🚀 What This Project Demonstrates

* Secure Authentication with JWT & Spring Security
* RESTful API Design Best Practices
* Layered Architecture (Controller → Service → Repository)
* DTO Pattern & Bean Validation
* Pagination, Sorting & Search
* Global Exception Handling
* Database Relationships using JPA/Hibernate
* Swagger/OpenAPI Documentation

---

## ✨ Features

* ✅ JWT Authentication with BCrypt Password Hashing
* ✅ User Profile with Statistics
* ✅ Blog Post Management (CRUD)
* ✅ Category Management
* ✅ Comment Management
* ✅ Post Like Functionality
* ✅ Search Posts by Title or Content
* ✅ Trending Posts (Most Liked)
* ✅ Pagination & Sorting
* ✅ DTO Pattern & Request Validation
* ✅ Global Exception Handling
* ✅ Swagger/OpenAPI Documentation
* ✅ Ownership Authorization Checks
* ✅ 24+ REST API Endpoints

---

## 🛠️ Tech Stack

| Technology          | Purpose                        |
| ------------------- | ------------------------------ |
| Java 21             | Programming Language           |
| Spring Boot 3.3.5   | Application Framework          |
| Spring Security     | Authentication & Authorization |
| Spring Data JPA     | Database Access Layer          |
| Hibernate           | ORM Framework                  |
| MySQL 8             | Relational Database            |
| JWT (jjwt 0.12.3)   | Token-Based Authentication     |
| BCrypt              | Password Hashing               |
| Lombok              | Boilerplate Reduction          |
| Swagger / OpenAPI 3 | API Documentation              |
| Maven               | Build Tool                     |

---

## 🏗️ Architecture

```text
Client
   │
   ▼
Controller Layer
   │
   ▼
Service Layer
   │
   ▼
Repository Layer
   │
   ▼
MySQL Database

Cross-Cutting Concerns:
├── JWT Authentication Filter
├── Spring Security
├── Bean Validation (@Valid)
├── Global Exception Handler
└── Swagger/OpenAPI
```

---

## 📋 API Endpoints

### 🔐 Authentication

| Method | Endpoint             | Description                  |
| ------ | -------------------- | ---------------------------- |
| POST   | `/api/auth/register` | Register a New User          |
| POST   | `/api/auth/login`    | Login and Generate JWT Token |

### 👤 Users

| Method | Endpoint                      | Description                      |
| ------ | ----------------------------- | -------------------------------- |
| GET    | `/api/users/{userId}/profile` | Get User Profile with Statistics |

### 📂 Categories

| Method | Endpoint               | Description        |
| ------ | ---------------------- | ------------------ |
| POST   | `/api/categories`      | Create Category    |
| GET    | `/api/categories`      | Get All Categories |
| GET    | `/api/categories/{id}` | Get Category by ID |
| PUT    | `/api/categories/{id}` | Update Category    |
| DELETE | `/api/categories/{id}` | Delete Category    |

### 📝 Posts

| Method | Endpoint                      | Description               |
| ------ | ----------------------------- | ------------------------- |
| POST   | `/api/posts?userId={id}`      | Create Post               |
| GET    | `/api/posts`                  | Get All Posts (Paginated) |
| GET    | `/api/posts/{id}`             | Get Post by ID            |
| PUT    | `/api/posts/{id}?userId={id}` | Update Post (Owner Only)  |
| DELETE | `/api/posts/{id}?userId={id}` | Delete Post (Owner Only)  |
| GET    | `/api/posts/user/{userId}`    | Get Posts by User         |
| GET    | `/api/posts/category/{id}`    | Get Posts by Category     |
| GET    | `/api/posts/search?keyword=x` | Search Posts              |
| POST   | `/api/posts/{id}/like`        | Like a Post               |
| GET    | `/api/posts/trending`         | Get Trending Posts        |

### 💬 Comments

| Method | Endpoint                                  | Description                 |
| ------ | ----------------------------------------- | --------------------------- |
| POST   | `/api/comments/post/{postId}?userId={id}` | Add Comment                 |
| GET    | `/api/comments/post/{postId}`             | Get Comments of a Post      |
| GET    | `/api/comments/{id}`                      | Get Comment by ID           |
| PUT    | `/api/comments/{id}?userId={id}`          | Update Comment (Owner Only) |
| DELETE | `/api/comments/{id}?userId={id}`          | Delete Comment (Owner Only) |
| GET    | `/api/comments/user/{userId}`             | Get User Comments           |

---

## 🔒 Security Flow

1. User Registers or Logs In
2. Server Generates JWT Token
3. Client Stores the Token
4. Token is Sent in the Authorization Header
5. JwtAuthFilter Validates the Token
6. Protected Resources Become Accessible

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 🚀 Run Locally

### Prerequisites

* Java 21+
* MySQL 8+
* Maven 3.6+

### Clone Repository

```bash
git clone https://github.com/shyam-singh-dev/blog-api.git
cd blog-api
```

### Create Database

```sql
CREATE DATABASE blog_api;
```

### Configure Database

Update your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_api
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run Application

```bash
./mvnw spring-boot:run
```

Or run:

```text
BlogApiApplication.java
```

from IntelliJ IDEA.

---

## 🌐 Access Application

| Resource     | URL                                   |
| ------------ | ------------------------------------- |
| API Base URL | http://localhost:8081                 |
| Swagger UI   | http://localhost:8081/swagger-ui.html |

---

## 📊 Database Schema

### 👤 Users

```text
users
├── id (PK)
├── name
├── email (UNIQUE)
├── password (BCrypt Hashed)
└── created_at
```

### 📂 Categories

```text
categories
├── id (PK)
├── name (UNIQUE)
└── description
```

### 📝 Posts

```text
posts
├── id (PK)
├── title
├── content (TEXT)
├── image_url
├── like_count
├── created_at
├── updated_at
├── user_id (FK → users.id)
└── category_id (FK → categories.id)
```

### 💬 Comments

```text
comments
├── id (PK)
├── content (TEXT)
├── created_at
├── post_id (FK → posts.id)
└── user_id (FK → users.id)
```

### 🔗 Relationships

```text
users (1) ────────< (N) posts
categories (1) ──< (N) posts
posts (1) ───────< (N) comments
users (1) ───────< (N) comments
```

---

## 📬 Sample API Usage

### 🔐 Register User

```bash
POST http://localhost:8081/api/auth/register
```

#### Request Body

```json
{
  "name": "Shyam Singh",
  "email": "shyam@gmail.com",
  "password": "pass123"
}
```

---

### 📝 Create Post

```bash
POST http://localhost:8081/api/posts?userId=1
Authorization: Bearer <JWT_TOKEN>
```

#### Request Body

```json
{
  "title": "My First Blog",
  "content": "Spring Boot is amazing...",
  "categoryId": 1
}
```

---

### 🔍 Search Posts

```bash
GET http://localhost:8081/api/posts/search?keyword=spring&page=0&size=10
```

---

### 📄 Get All Posts

```bash
GET http://localhost:8081/api/posts?page=0&size=10&sortBy=createdAt&sortDir=desc
```

---

### 📖 Get Post By ID

```bash
GET http://localhost:8081/api/posts/1
```

---

### 💬 Add Comment

```bash
POST http://localhost:8081/api/comments/post/1?userId=1
Authorization: Bearer <JWT_TOKEN>
```

#### Request Body

```json
{
  "content": "Great article! Very helpful."
}
```

---

## 🎯 Key Highlights

* ✅ 24+ REST API Endpoints
* ✅ JWT Stateless Authentication
* ✅ Custom JPQL Queries
* ✅ Aggregation Queries using GROUP BY
* ✅ Pagination, Sorting & Filtering
* ✅ Search Functionality
* ✅ Ownership Authorization Checks
* ✅ DTO Pattern & Validation
* ✅ Global Exception Handling
* ✅ Swagger/OpenAPI Documentation
* ✅ Clean Layered Architecture

---

## 📧 Contact

**Shyam Singh Parmar**

* 🐙 GitHub: https://github.com/shyam-singh-dev
* 📧 Email: [shyamparmar.dev@gmail.com](mailto:shyamparmar.dev@gmail.com)
* 💼 LinkedIn: Shyam Singh Parmar

---

## 📄 License

This project is licensed under the **MIT License**.

---

## ⭐ Support

If you found this project useful:

* ⭐ Star this repository
* 🍴 Fork it for learning purposes
* 🛠️ Contribute improvements and new features

Your support helps the project grow and reach more developers.
