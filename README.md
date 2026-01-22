# Technical Test - Ecommerce (Spring Boot)

## Content

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Application Structure](#application-structure)
- [Architecture Diagram](#architecture-diagram)
- [How to build and run](#how-to-build-and-run)
    - [Pre-requisites](#pre-requisites)
    - [Step 1 - BUILD](#step-1---build)
    - [Step 2 - RUN](#step-2---run)
- [URLs and Endpoints](#urls-and-endpoints)
- [Testing](#testing)
    - [How to run test](#how-to-run-test)
    - [How to generate a coverage report](#how-to-generate-a-coverage-report)

## Overview

This Spring Boot application exposes a rest service to consult the final price of a product.

It was designed to comply with a [**Hexagonal Architecture**](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) and also to comply with [**SOLID principles**](https://en.wikipedia.org/wiki/SOLID).

## Technology Stack

* **Common**
    * [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
    * [Maven 3.x](https://maven.apache.org/)
    * [Spring Boot v4](https://spring.io/projects/spring-boot)
    * [Lombok](https://projectlombok.org/): Helper to create builders, setters, getters, etc.
    * [MapStruct](https://mapstruct.org/): Helper to create mappers to pass objects between the different layers.
    * [JaCoCo](https://www.eclemma.org/jacoco/): Used for code coverage reports.
* **Data & Persistence**
    * [Spring Data JPA](https://spring.io/projects/spring-data-jpa): JPA based repositories implementation.
    * [Flyway](https://flywaydb.org/): To load database migrations at application startup.
    * [H2 Database](https://www.h2database.com/): Very fast, open source, JDBC API. Support embedded and server modes;
      disk-based or in-memory databases
* **RestAPI**
    * [springdoc-openapi-ui](https://springdoc.org/): Helps to automate the generation of API documentation using spring boot projects.
    * [rest-assured](https://rest-assured.io/): REST Assured is a Java DSL for simplifying testing of REST based services.

## Application Structure

| Module                     | Description                                                                           |
|----------------------------|---------------------------------------------------------------------------------------|
| ecommerce-parent           | Module for inheriting common configurations, dependencies, and plugins.               |
| - ecommerce-application    | Part of the business logic is centralized in this layer.                              |
| - ecommerce-bootloader     | Module to launch Spring Boot application, run tests and coverage reports.             |
| - ecommerce-domain         | Here the POJO classes and domain interfaces are centralized.                          |
| - ecommerce-infrastructure | Adapters such as Rest and access to Persistence (JPA) are centralized in this module. |

## Architecture Diagram

![Architecture Diagram](docs/architecture_diagram.png)

## How to Build and Run

### Pre-requisites

* Java 21
* Maven 3.8.x

### Step 1 - BUILD

Go to the project root directory and execute the following command to compile, test, package and install the different
artifacts in your local maven repository.

```shell
mvn clean install
```

Or use the embedded **Maven Wrapper** if you don't have a maven installation.

```shell
./mvnw clean install
```

### Step 2 - RUN

> **IMPORTANT:** Make sure you do not have any applications running on port `8080` before running this program.

After creating all artifacts you can run the project with the following command:

```shell
mvn spring-boot:run -pl ecommerce-bootloader
```

Or use the embedded **Maven Wrapper** if you don't have a maven installation.

```shell
./mvnw spring-boot:run -pl ecommerce-bootloader
```

### Step 3 - DONE!

You should see in the console the following log line:

```log
......
2024-04-25T01:29:26.228-03:00  INFO 45458 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-04-25T01:29:26.234-03:00  INFO 45458 --- [           main] c.e.b.EcommerceSpringBootApplication     : Started EcommerceSpringBootApplication in 1.841 seconds (process running for 1.959)
```

## URLs and Endpoints

| URL                                   | Description                                                                                                         |
|---------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| http://localhost:8080/swagger-ui.html | Swagger interface based on the OpenAPI auto-generated schema that helps you to test the `prices` resource endpoint. |
| http://localhost:8080/v3/api-docs     | OpenAPI schema auto-generated from the swagger annotation provided by the `springdoc` dependency.                   |
| http://localhost:8080/api/v1/prices   | GET http method that will receive three parameters.                                                                 |
| http://localhost:8080/h2-console      | H2 Database Console (**URL:** `jdbc:h2:mem:test`, **User:** `sa`, **Password:** `password`)                         |

## Testing

### How to run test

```shell
mvn clean test
```

Or use the embedded **Maven Wrapper** if you don't have a maven installation.

```shell
./mvnw clean test
```

### How to generate a coverage report

> **Note:** This report will be generated through the **JaCoCo** library

```shell
mvn clean verify
```

Or use the embedded **Maven Wrapper** if you don't have a maven installation.

```shell
./mvnw clean verify
```

#### Done!

The code coverage report will be generated in the following path: `ecommerce-bootloader/target/index.html`