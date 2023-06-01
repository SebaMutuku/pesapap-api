# Get Started

## Swagger documentation
Make sure the application is up and access it via link the endpoint `URI/swagger-ui/index.html e.g http://localhost:9096/swagger-ui/index.html`

## About Pesapap -api version 1
This is a student registration, querying and fees payment api with disabled Spring Oauth2 JWT authentication and disabled Password encryption using BCrypt Role-based authorization with Spring Security Customized access denied handling Technologies

Spring Boot 3.0 Spring Security JSON Web Tokens (JWT) BCrypt Maven Getting Started

To get started with this project, you will need to have the following installed on your local machine:

## Dependencies
JDK 17+ Maven 3+ To build and run the project, follow these steps:

## Setting up the project
Clone the repository with the command `git clone https://github.com/SebaMutuku/pesapap-api.git` for https or ` git clone git@github.com:SebaMutuku/pesapap-api.git` for ssh
Navigate to the project directory and :  
1. Build the project: `mvn clean install -DskipTests` or `mvn clean install`. This will build a jar file in target folder
2. If you are running the application as a standalone jar file, be sure to copy `application.yml` into the same location as the jar file and run using command `java -jar jarname.jar &`. & is included to run it on background.
3. If you are running the project using an IDE or from command line use: `mvn spring-boot:run` .
4. The application will be available on http://localhost:9096.

You can also build it with docker file using the command `docker build -t pesapap-api .`
