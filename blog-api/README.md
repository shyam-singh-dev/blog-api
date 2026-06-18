# 📝 Blog API

A secure REST API for blog management built with Spring Boot 3, featuring JWT authentication, JPA relationships, and Swagger documentation.

[![Java](https://img.shields.io/badge/Java-21-orange)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)]()
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)]()
[![JWT](https://img.shields.io/badge/JWT-Authentication-yellow)]()

---

## 🚀 Features (Coming Soon)

- ✅ User Authentication (JWT + BCrypt)
- ✅ Blog Post CRUD Operations
- ✅ Category Management
- ✅ Comments System
- ✅ Like/Unlike Posts
- ✅ Search & Filter Posts
- ✅ Pagination
- ✅ DTO Pattern with Validation
- ✅ Global Exception Handling
- ✅ Swagger/OpenAPI Documentation

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 21** | Programming language |
| **Spring Boot 3.3** | Framework |
| **Spring Security** | Authentication |
| **Spring Data JPA** | ORM |
| **MySQL 8** | Database |
| **JWT** | Token-based auth |
| **BCrypt** | Password hashing |
| **Lombok** | Boilerplate reduction |
| **Swagger** | API documentation |
| **Maven** | Build tool |

---

## 📋 Project Structure

blog-api/
├── src/main/java/com/blog/blog_api/
│ ├── config/ # Security + Swagger config
│ ├── controller/ # REST controllers
│ ├── dto/
│ │ ├── request/ # Request DTOs
│ │ └── response/ # Response DTOs
│ ├── entity/ # JPA entities
│ ├── exception/ # Custom exceptions
│ ├── repository/ # JPA repositories
│ ├── security/ # JWT classes
│ └── service/ # Business logic
├── src/main/resources/
│ └── application.properties
├── pom.xml
└── README.md

---

## 🏗️ Entities

- **User** - Blog users (Authentication)
- **Category** - Post categories
- **Post** - Blog posts
- **Comment** - Post comments

---

## 🔗 Relationship

- **User (1) ←→ (Many) Post**
- User (1) ←→ (Many) Comment
- Category (1) ←→ (Many) Post
- Post (1) ←→ (Many) Comment

---

## 🚀 Run Locally

### Prerequisites

- Java 21+
- MySQL 8+
- Maven 3.6+

### Setup Steps

**1. Clone the repository**
```bash
git clone https://github.com/shyam-singh-dev/blog-api.git
cd blog-api
