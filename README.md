# RideShare Backend

A minimal ride sharing backend built with Spring Boot 3, MongoDB, JWT authentication, and clean architecture.

## Features
- User and Driver registration/login
- JWT-based authentication
- Request, accept, and complete rides
- Role-based access control
- Global exception handling

## Endpoints
- `POST /api/auth/register` — Register user/driver
- `POST /api/auth/login` — Login and get JWT
- `POST /api/v1/rides` — User requests ride
- `GET /api/v1/user/rides` — User views own rides
- `GET /api/v1/driver/rides/requests` — Driver views pending rides
- `POST /api/v1/driver/rides/{id}/accept` — Driver accepts ride
- `POST /api/v1/rides/{id}/complete` — Complete ride

## Tech Stack
- Spring Boot 3
- MongoDB
- Spring Security (JWT)
- Lombok
- Jakarta Validation

## How to Run
1. Ensure MongoDB is running locally on port 27017.
2. Build and run the project:
   ```sh
   ./mvnw spring-boot:run
   ```
3. API runs on `http://localhost:8081`

## Assignment Requirements
- Clean architecture: controller → service → repository
- Spring Boot 3 security (SecurityFilterChain)
- MongoDB with Spring Data
- JWT for authentication
- BCrypt for password encoding
- Jakarta Validation in DTOs
- Global Exception Handling

---
