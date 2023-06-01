# pesapap-api

## Swagger documentation
Make sure the application is up and access it via link the endpoint [Swagger endpoint. Be sure to edit it](http or https://{ip}:9096/swagger-ui/index.html)

## Build and run the application
User registration and login with JWT authentication Password encryption using BCrypt Role-based authorization with Spring Security Customized access denied handling Technologies

Spring Boot 3.0 Spring Security JSON Web Tokens (JWT) BCrypt Maven Getting Started

To get started with this project, you will need to have the following installed on your local machine:

JDK 17+ Maven 3+ To build and run the project, follow these steps:

Clone the repository: git clone [Https repo url](https://github.com/SebaMutuku/pesapap-api.git) for https and [Ssh repo url](git@github.com:SebaMutuku/pesapap-api.git) 
Navigate to the project directory and :  
1. Build the project: `mvn clean install -DskipTests`. This will build a jar file in target folder
2. Run the project: mvn spring-boot:run -> The application will be available at http://localhost:9096.

You can also build it with docker file. `docker build -t pesapap-api .`
