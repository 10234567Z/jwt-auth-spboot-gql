# Spring Boot JWT Auth with GraphQL & PostgreSQL

## Table of Contents
- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## About

This project is a **JWT-based authentication system** built with **Spring Boot** and **GraphQL**, integrated with a **PostgreSQL database**. It provides a secure and scalable authentication mechanism for GraphQL APIs, allowing users to sign up, log in, and manage their authentication tokens efficiently.

## Features

- 🔐 **JWT-based authentication** for secure API access
- 🗄 **PostgreSQL integration** for user management
- ⚡ **GraphQL API** for efficient data fetching
- 🎯 **Role-based access control**
- 📂 **Environment-based configuration**
- 🚀 **Spring Boot-powered backend**

## Tech Stack

- **Spring Boot** - Backend framework
- **GraphQL** - Query language for APIs
- **PostgreSQL** - Relational database management
- **JWT (JSON Web Token)** - Secure authentication
- **Spring Security** - Authentication & Authorization
- **Maven** - Dependency management

## Getting Started

### Prerequisites

Before starting, ensure you have the following installed:

- **PostgreSQL** installed
- **Maven** installed / **IntelliJ IDEA** installed to run the project on your locals with Maven
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/10234567Z/jwt-auth-spboot-gql.git
   cd jwt-auth-spboot-gql
   ```

2. **Create a `.env.properties` file in the root directory:**
   ```properties
   DATASRC_USERNAME=your_database_username
   DATASRC_PWD=your_database_password
   DATASRC_URL=your_database_url
   JWT_SECRET=your_jwt_secret_key
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access GraphQL API:**
   - Open **Postman** or any GraphQL client.
   - Navigate to `http://localhost:8080/graphql`
   - Load predefined queries and modify them with your own values.

## Usage

Once the server is running, you can:

1. **Register a new user**
2. **Authenticate with JWT**
3. **Execute GraphQL queries with authorization headers**

## Configuration

- Edit `application.properties` to change database settings.
- Modify GraphQL schemas under `/src/main/resources/graphql`.
- Update JWT expiration settings in `SecurityConfig.java`.

## Contributing

Contributions are welcome! Feel free to submit a pull request.

## License

This project is licensed under the MIT License.

