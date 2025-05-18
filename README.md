SELECT * FROM ecommerce_db.sales;

# Spring Boot Product Management REST API

This is a simple Spring Boot application for managing products. It exposes a RESTful API to perform basic CRUD operations (Create, Read, Update, Delete) on products.

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Hibernate

---


### Setup Instructions

1. **Install MySQL** if you haven't already.

2. **Set up MySQL root user** and create a password.

3. **Configure database connection:**

   In `src/main/resources/application.properties`, set the MySQL username and password like this:

   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_mysql_root_password
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.jpa.hibernate.ddl-auto=update

### Prerequisites
- Java 17+
- MySQL installed and running
- Maven

### 1. Clone the repository

```bash
git clone https://github.com/your-username/product-api-springboot.git
cd product-api-springboot
