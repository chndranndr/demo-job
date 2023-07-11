
# Job API
This project provides a RESTful API for retrieving job data from an external API and requires authentication using JSON Web Tokens (JWT).

## Technologies Used
- Java Spring Boot
- Spring Data JPA
- JWT (JSON Web Tokens)
- RestTemplate
- Lombok

## Getting Started
Prerequisites:

- Java 8 or higher
-  Maven

## Setup:
Clone the repository:

    git clone https://github.com/your-username/job-api.git

Build the project:

    mvn clean install

Run the application:

    mvn spring-boot:run

The application will start running on http://localhost:8080.

## Endpoints
1. Login
   URL: /login
   Method: POST
   Request Body: JSON payload containing username and password
   Response: JWT token
2. Get Job List
   URL: /jobs
   Method: GET
   Request Header: Authorization with JWT token (Bearer <token>)
   Response: JSON payload containing the list of jobs
3. Get Job Detail
   URL: /jobs/{id}
   Method: GET
   Request Header: Authorization with JWT token (Bearer <token>)
   Path Variable: id - ID of the specific job
   Response: JSON payload containing the job detail
