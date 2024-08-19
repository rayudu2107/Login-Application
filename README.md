# Login Application

## Overview

This is a Spring Boot application that provides user authentication and management features. It uses JWT for authentication and offers endpoints for user registration, login, and management. The application is designed to handle user authentication, user registration, and CRUD operations for user management with role-based access control.

## Features

- **User Registration**: Allows new users to register with their email, password, and other details.
- **User Login**: Authenticates users and provides a JWT for access to protected resources.
- **User Management**: Admins can perform CRUD operations on user data, including finding, updating, and deleting users.
- **Validation**: Ensures that user inputs are valid and properly formatted.

## Endpoints

### Authentication

- **POST /api/v1/auth/register**
  - Registers a new user.
  - **Request Body**: `RegisterRequest` (email, password, firstName, lastName, phoneNumber)
  - **Response**: `UserResponse`

- **POST /api/v1/auth/login**
  - Authenticates a user and returns a JWT.
  - **Request Body**: `LoginRequest` (email, password)
  - **Response**: `JwtAuthenticationResponse` (JWT token)

### User Management (Admin Only)

- **GET /api/v1/home/{id}**
  - Retrieves user details by ID.
  - **Path Variable**: `id` (user ID)
  - **Response**: `UserResponse`

- **PUT /api/v1/home/{id}**
  - Updates user details by ID.
  - **Path Variable**: `id` (user ID)
  - **Request Body**: `UpdateRequest` (firstName, lastName, phoneNumber)
  - **Response**: `UserResponse`

- **DELETE /api/v1/home/{id}**
  - Deletes a user by ID.
  - **Path Variable**: `id` (user ID)
  - **Response**: HTTP 204 No Content

- **GET /api/v1/home/users**
  - Retrieves a list of all users.
  - **Response**: List of `UserResponse`

## Exception Handling

The application handles various exceptions and returns meaningful error responses:

- **UserAlreadyExistsByEmailException**: HTTP 400 Bad Request
- **BadCredentialsException**: HTTP 401 Unauthorized
- **UserNotFoundByIdException**: HTTP 404 Not Found
- **UsersDataNotFoundException**: HTTP 404 Not Found
- **AccessDeniedException**: HTTP 403 Forbidden
- **MethodArgumentNotValidException**: HTTP 400 Bad Request (validation errors)

## API Documentation

The API documentation is available via Swagger at the following URL:

[API Documentation](http://localhost:8080/swagger-ui.html)

## Configuration

- **Security**: JWT-based authentication with bearer token scheme.
- **OpenAPI Documentation**: Configured using Swagger for API documentation.

## Running the Application

1. Clone the repository.
2. Build the application using Maven or Gradle.
3. Run the application using your preferred IDE or with the command line.
4. Access the application at `http://localhost:8080`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

