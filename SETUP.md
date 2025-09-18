# CRMS Application Setup Manual

## Prerequisites

- **Java 17+** (for Spring Boot 6)
- **Maven 3.8+**
- **MySQL 8.x** (with InnoDB)
- **Node.js 18+** (for frontend)
- **Git**
- **Eclipse IDE** (recommended, but any IDE works)

---

## 1. Clone the Repository

```bash
git clone <your-repo-url>
cd CRMS
```

---

## 2. Database Setup

**2.1. Create a MySQL database:**

```sql
CREATE DATABASE crms_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'crms_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON crms_db.* TO 'crms_user'@'localhost';
FLUSH PRIVILEGES;
```

**2.2. Update database credentials** in `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/crms_db
spring.datasource.username=crms_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 3. Backend Setup (Spring Boot)

1. **Build the project:**

```bash
mvn clean install
```

2. **Run the application:**

```bash
mvn spring-boot:run
```

- The API will be available at: `http://localhost:8080/api/v1/`

---

## 4. Initial Data Seeding

- The backend may include a `DataSeeder` class to populate initial data.
- On first run, check logs for seeding status or manually insert required roles/users.

---

## 5. Common Issues

- **Port conflicts:** Ensure ports 8080 (backend) and 3306 (MySQL) are free.
- **Database connection errors:** Double-check credentials and DB status.

---

## 6. Useful Commands

- **Stop application:** `Ctrl+C` in terminal
- **Rebuild:** `mvn clean install`
- **View logs:** Check `logs/` directory or console output

---

## 7. Additional Notes

- For production, configure environment variables and secure credentials.
- Review `application.properties` for further customization.

---

**End of Setup Manual**