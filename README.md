# 🚗 Bansal Motors - Car Showroom Management Backend

A full-featured backend application built using **Spring Boot**, **Spring Data JPA**, and **REST APIs** to manage a modern car showroom's operations. This system handles car inventory, bookings, showroom staffing, and customer records with clean modular design and scalable architecture.

---

## 🧱 Tech Stack

| Component     | Tech                            |
|---------------|---------------------------------|
| Language      | Java 17+                        |
| Framework     | Spring Boot                     |
| ORM           | Spring Data JPA + Hibernate     |
| Database      | MySQL                           |
| API           | REST APIs (Spring Web)          |
| Tools         | Lombok, Maven                   |
| Architecture  | DTO + Entity + Service + Mapper |

---

## 📁 Project Structure

```
BansalMotors/
├── controllers/            # REST controllers for each entity
├── services/               # Business logic (CRUD + filters)
├── repositories/           # Spring Data JPA Repositories
├── entities/               # JPA Entity classes (Car, Customer, etc.)
├── dtos/                   # DTOs used for request/response abstraction
├── mappers/                # Entity <-> DTO converters
├── advice/                 # Global exception handling (ResponseAPI)
├── exceptions/             # Custom exception classes
├── application.properties  # DB & JPA configs
└── BansalMotorsApplication.java  # Spring Boot entry point
```

---

## 🚀 Features

- ✅ CRUD operations for Cars, Customers, Bookings, Showrooms, and Staff
- 🔍 Search filters by brand, model, showroom, role, availability, etc.
- 📅 Booking management with date & status filters (Confirmed, Pending)
- 🏢 Showroom utilities: get active showrooms, with staff/cars
- 🔁 DTO–Entity conversion for clean request/response handling
- 🧾 Global exception handling using `@ControllerAdvice`
- 🏗️ Clean layered architecture (Controller → Service → Repository)
- 📦 Future scope: Validation, Pagination, Logging, Security

---

## 📨 API Endpoints Overview

```
GET    /api/cars               → Get all cars
GET    /api/customers/{id}     → Get customer by ID
POST   /api/bookings           → Create a booking
PUT    /api/staff/{id}         → Update staff member
DELETE /api/showrooms/{id}     → Delete showroom
...
```

## 📘 API Documentation (Swagger)

Once the app is running, access your Swagger UI here:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

All endpoints are auto-documented using [OpenAPI 3 (springdoc-openapi)](https://springdoc.org/).


---

## ⚙️ Setup Instructions

1. **Clone the repo**
```bash
git clone https://github.com/WorkMohit17/Car-Showroom
cd bansal-motors
```

2. **Configure the DB**  
In `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bansalmotors
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the application**
```bash
./mvnw spring-boot:run
```

4. **Test APIs**  
Use Postman or Swagger UI.


## 💡 Future Improvements

-  Add bean validation to DTOs using `javax.validation`
-  Enable logging using `slf4j` and `Logback`
-  Add pagination and sorting via `Pageable`
-  Secure APIs using Spring Security & JWT
-  Dockerize application for deployment


## 🧑‍💻 Author
**Mohit Bansal**  
🔗 [GitHub](https://github.com/WorkMohit17) | 💼 [LinkedIn](https://www.linkedin.com/in/workmohit17/)


## 📜 License

 This project is open source and available under the MIT License.

