## gRPC Client Server

gRPC CRUD Operation Service using Java and Postgres

## Tech Stack
1. Java (plain)
2. Hibernate
3. gRPC
4. Postgres

## Prerequisite
1. Java 18
2. Maven 3.8.6
3. Intellij IDEA 2022
4. Docker 4.12.0

## How to Build
`mvn compile package`

## How to Run

Spin up the postgres database  
`docker compose up -d`

Run the gRPC server  
In your Intellij code editor go to `src/main/java/com.learning.grpc/GrpcServer` then click the play button or `^R` to run current class

Run the gRPC client  
In your Intellij code editor go to `src/main/java/com.learning.grpc/GrpcClient` then click the play button or `^R` to run current class

By default, the client will retrieve all users stored in database by calling `getAllUsers` RPC method defined in the server side. The result is outputted to the terminal and the process finished.

## How to Test
The tests are all about CRUD operation in database

Run all the tests: `mvn test`

To run a specific CRUD operation test:  
Go to `src/test/com.learning.grpc.server.entity/UserDAOTest`  
Select a test then click the button besides the test name or press `Ctrl + Shift + R`  
You can run `create` test to add your own user data to the database

## Features
[V] Create user  
[V] Get all users   
[V] Get a user  
[ ] Update  
[ ] Delete  
[ ] Client can choose a particular operation from terminal
