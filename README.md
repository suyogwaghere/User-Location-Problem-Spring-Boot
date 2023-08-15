##USER:
Covert it into Proper readme file for git repo
Included User Location Problem
Create a spring boot project using Gradle with the following requirements

1. There will be 2 users (ADMIN, READER).
2. ADMIN can perform CRUDE operations like POST, DELETE, PATCH, etc.
3. READER can perform only GET operation.
4. Create a REST API called create_data which creates a table in the database with the name
   'user_location'.
5. user_location will have three fields NAME, Latitude, Longitude and excluded.
6. user can update the user_location table using another REST API called update_data
7. READER can call the get_users/N which returns the nearest N users from the location (0,0).
   E.g GET http://base_url/get_users/5 will return the nearest 5 users from (0,0) which are not
   excluded;
   You need to create 3 rest API
   a) create_data b) update_data c) get_users/N
   Evaluation will be done on the following criteria
8. Unit Test
9. Checkstyle issues
10. Working functionality
11. Clean and readable code with proper Javadoc with minimal code smell
    Note: Use HSQL in-memory database for the database work. Your code should be able to
    create a table with no manual script required.

##ASSISTANT:

# User Location REST API

This is a Spring Boot project that implements a REST API for managing user locations with specific roles and functionalities. The project uses Gradle as the build tool and an HSQL in-memory database for data storage.

## Table of Contents

- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Evaluation Criteria](#evaluation-criteria)

## Requirements

1. There will be 2 users: ADMIN and READER.
2. ADMIN can perform CRUD operations like POST, DELETE, PATCH, etc.
3. READER can perform only the GET operation.
4. Create a REST API called `create_data` which creates a table in the database with the name 'user_location'.
5. `user_location` will have three fields: `NAME`, `Latitude`, `Longitude`, and `excluded`.
6. Users can update the `user_location` table using another REST API called `update_data`.
7. READER can call the `get_users/N` API, which returns the nearest N users from the location (0,0).

## Getting Started

1. Clone the repository:

```bash
git clone <repository-url>
cd user-location-rest-api
```

2. Build and run the application:

```bash
./gradlew bootRun
```

The application will be accessible at `http://localhost:8080`.

## API Endpoints

### Create User Location (POST)

Create a new user location.

- **URL:** `/api/user_location/create_data`
- **Method:** POST
- **Body:**
  ```json
  {
    "name": "John",
    "latitude": 12.345,
    "longitude": -67.89
  }
  ```
- **Roles Required:** ADMIN
- **Response:** Returns the created user location.

### Update User Location (PUT)

Update an existing user location.

- **URL:** `/api/user_location/update_data/{id}`
- **Method:** PUT
- **Path Variable:** `id` - ID of the user location to update
- **Body:**
  ```json
  {
    "name": "Updated Name",
    "latitude": 11.111,
    "longitude": -22.222,
    "excluded": false
  }
  ```
- **Roles Required:** ADMIN
- **Response:** Returns the updated user location.

### Get Nearest Users (GET)

Get the nearest N users from the location (0,0).

- **URL:** `/api/user_location/get_users/{count}`
- **Method:** GET
- **Path Variable:** `count` - Number of nearest users to retrieve
- **Roles Required:** READER
- **Response:** Returns a list of nearest users.

## Evaluation Criteria

The project will be evaluated based on the following criteria:

1. **Unit Test:** The code should be accompanied by appropriate unit tests that cover key functionalities.
2. **Checkstyle Issues:** The code should adhere to good coding practices and have minimal Checkstyle issues.
3. **Working Functionality:** The implemented APIs should perform as described in the requirements.
4. **Clean and Readable Code:** The code should be well-organized, readable, and have proper comments and documentation.
5. **Javadoc:** Relevant methods and classes should be documented using Javadoc comments.
6. **Code Smell:** The code should have minimal code smells and follow best practices.

Note: This readme provides a brief overview of the project. For more details, please refer to the source code and associated comments.
