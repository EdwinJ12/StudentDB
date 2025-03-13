# Student Management System

This is a **Spring Boot + MongoDB** application for managing student records with a **Thymeleaf frontend**.

## Features
- Add, delete, update, and retrieve student records
- Search students by **name, age, or course**
- Prevent duplicate student IDs
- Generate **random students** with valid names, IDs, and courses
- REST API with `ResponseEntity` for structured responses
- **Thymeleaf UI** for easy interaction

## Technologies Used
- **Spring Boot** (Spring MVC, Spring Data MongoDB)
- **MongoDB** (as the database)
- **Thymeleaf** (for frontend UI)
- **Lombok** (to reduce boilerplate code)
- **Maven** (for dependency management)

## Installation
1. Clone the repository:  
   ```sh
   git clone https://github.com/your-username/student-management.git
   cd student-management
   ```
2. Configure **MongoDB** (make sure MongoDB is running locally or update `application.properties` with your connection URI).  
3. Build and run the project:  
   ```sh
   mvn spring-boot:run
   ```
4. Access the **UI** at:  
   ```
   http://localhost:8080/ui/students/home
   ```

## API Endpoints
| Method  | Endpoint                            | Description |
|---------|-------------------------------------|-------------|
| `POST`  | `/students/addStudent`             | Add a student |
| `GET`   | `/students/list`                   | Get all students |
| `GET`   | `/students/student/{id}`           | Get student by ID |
| `DELETE`| `/students/deleteStudentById/{id}` | Delete student by ID |
| `DELETEAll`| `/students/wipe`                   | Delete all students |
| `GET`   | `/students/search?name=John`       | Search by name |
| `GET`   | `/students/search?age=20`          | Search by age |
| `GET`   | `/students/search?course=human resource`       | Search by course |
| `POST`  | `/students/addRandomStudent`       | Add a randomly generated student |

## Notes
- **ID length** is restricted to **8 characters**.
- Searching for `John` will return all names containing "John" (e.g., "John Doe" , "Paul John").
- The application uses **MongoDB** and does **not** use JPA.

## License
This project is open-source. Feel free to use and modify it.

---

Happy coding! 🚀

---
### Generated with ChatGPT  
This README file was created with the assistance of ChatGPT to document the Student Database project.
