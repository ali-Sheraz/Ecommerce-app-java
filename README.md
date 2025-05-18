# 🛒 Ecommerce Product Management API

A simple **Spring Boot** application for managing products, inventory, and sales.  
Provides RESTful endpoints for CRUD operations and reporting in an ecommerce context.

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

3. **Create the database:**

   ```sql
   CREATE DATABASE ecommerce_db;

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
git clone https://github.com/ali-Sheraz/Ecommerce-app.git
cd Ecommerce-app


mvn clean install
mvn spring-boot:run

## API Endpoints Overview

### Inventory API (`/api/inventory`)

| HTTP Method | Endpoint                      | Description                                                                           |
|-------------|-------------------------------|---------------------------------------------------------------------------------------|
| GET         | `/api/inventory/{productId}`  | Retrieve inventory details for a specific product by its product ID.                  |
| DELETE      | `/api/inventory/{id}`          | Delete an inventory record by its inventory ID.                                      |
| GET         | `/api/inventory`               | Retrieve a list of all inventory records.                                            |
| GET         | `/api/inventory/low-stock`    | Retrieve products with low stock. Optional query parameter `threshold` (default 10). |
| PUT         | `/api/inventory/update`        | Update inventory quantity. Requires `productId`, `quantity`, and optional `updatedBy` (default "admin"). |

---

### Product API (`/api/products`)

| HTTP Method | Endpoint                  | Description                              |
|-------------|---------------------------|------------------------------------------|
| POST        | `/api/products/register`  | Create a new product.                   |
| GET         | `/api/products`           | Retrieve all products.                  |
| GET         | `/api/products/{id}`      | Retrieve product details by product ID. |
| PUT         | `/api/products/{id}`      | Update product information by ID.      |
| DELETE      | `/api/products/{id}`      | Delete a product by its ID.             |

---

### Sales API (`/api/sales`)

| HTTP Method | Endpoint                             | Description                                                                                     |
|-------------|------------------------------------|------------------------------------------------------------------------------------------------|
| GET         | `/api/sales/{id}`                   | Retrieve sale details by sale ID.                                                             |
| DELETE      | `/api/sales/{id}`                   | Delete a sale record by its ID.                                                                |
| GET         | `/api/sales/revenue`                | Get total revenue for a specified period (`daily`, `weekly`, `monthly`, `yearly`). Optional `start` and `end` date parameters supported for custom range. |
| GET         | `/api/sales/compare`                | Compare revenue between two date ranges. Requires `start1`, `end1`, `start2`, and `end2` parameters (format: YYYY-MM-DD). |
| GET         | `/api/sales/by-date`                | Retrieve sales within a date range. Parameters: `start` and `end` (format: YYYY-MM-DD).         |
| GET         | `/api/sales/by-product/{productId}`| Retrieve all sales for a specific product by product ID.                                       |
| GET         | `/api/sales/by-category`            | Retrieve sales filtered by product category. Parameter: `category`.                           |

---

