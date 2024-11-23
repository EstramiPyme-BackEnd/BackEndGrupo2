# Estramipymes API

## 📋 Description
REST API developed with Spring Boot to manage users, students, teachers, and administrators of the Estramipymes platform. The API provides endpoints for CRUD operations (Create, Read, Update, and Delete) for each type of user.

## 🛠 Technologies Used
- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- MySQL
- Lombok
- Maven
- java-dotenv (for environment variables management)

## 📦 Project Structure
```
com.example.estramipymes/
├── config/
│   └── CorsConfig.java
├── controller/
│   ├── AdminController.java
│   ├── StudentController.java
│   ├── TeacherController.java
│   └── UserController.java
├── model/
│   ├── Admin.java
│   ├── Student.java
│   ├── Teacher.java
│   └── User.java
├── repository/
│   ├── AdminRepository.java
│   ├── StudentRepository.java
│   ├── TeacherRepository.java
│   └── UserRepository.java
├── service/
│   ├── AdminService.java
│   ├── StudentService.java
│   ├── TeacherService.java
│   └── UserService.java
├── util/
│   └── EncryptionUtil.java
└── BackEstramipymesApplication.java
```

## 🚀 Features
- User management with different roles (Admin, Teacher, Student, User)
- Password encryption
- Data validation
- Custom error handling
- CORS configuration for local development
- Automatic registration dates support
- Environment variables configuration

## 📡 Endpoints

### Users
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `GET /users?email={email}` - Search user by email
- `POST /users` - Create new user
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

### Administrators
- `GET /admin` - Get all administrators
- `GET /admin/{id}` - Get administrator by ID
- `POST /admin` - Create new administrator
- `PUT /admin/{id}` - Update administrator
- `DELETE /admin/{id}` - Delete administrator

### Teachers
- `GET /teacher` - Get all teachers
- `GET /teacher/{id}` - Get teacher by ID
- `POST /teacher` - Create new teacher
- `PUT /teacher/{id}` - Update teacher
- `DELETE /teacher/{id}` - Delete teacher

### Students
- `GET /student` - Get all students
- `GET /student/{id}` - Get student by ID
- `POST /student` - Create new student
- `PUT /student/{id}` - Update student
- `DELETE /student/{id}` - Delete student

## 🔧 Configuration

### Prerequisites
- JDK 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

### Environment Variables
The project uses a `.env` file to manage environment variables. Create a `.env` file in the project root with the following variables:

```env
DB_USER=your_mysql_user
DB_PASSWORD=your_mysql_password
DB_HOST=localhost
DB_PORT=3306
DB_NAME=database_name
```

These variables are automatically loaded at application startup through the `java-dotenv` library.

### Database Configuration
The `application.properties` file will use the environment variables defined in `.env`. Configuration example:

```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Installation and Execution

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd estramipymes
```

3. Create and configure the `.env` file with your environment variables

4. Compile the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 🔐 Security
- AES encryption implementation for passwords
- Data validation in all CRUD operations
- Role-based access control
- Secure exception handling
- Sensitive variables protected through .env file

## ⚠️ Important Notes
- The application is configured to allow CORS from `http://localhost:4200`
- Passwords are stored encrypted in the database
- Authentication is required to access most endpoints
- Timestamps are handled in "yyyy-MM-dd" format
- Make sure not to share or commit the `.env` file in version control
- Add `.env` to your `.gitignore` file

## 📄 License
This project is under the [License Name]

## 👥 Contribution
Contributions are welcome. Please open an issue first to discuss any changes you would like to make.
