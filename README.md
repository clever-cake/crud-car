# crud-car

This is just a showcase of a simple CRUD (actually without the ability to delete something) application which handles http request and stores the data in a postgres db.
Involved technologies:
- Spring Boot
- Spring Web
- Spring Data JPA
- Postgres
- Testcontainers
- AssertJ
- Java 18
- docker compose


In a real world scenario such a simple use case could be realized by using [Apache isis](https://isis.apache.org/) or by using [Spring Data Rest](https://spring.io/projects/spring-data-rest)


# Getting started

This application needs a connection to a postgres database. The database can be easily setup by using docker-compose. Simply run 

```
docker-compose up -d
```

or if you use docker compose v2 

```
docker compose up -d
```

The data is stored in a docker volumes.

## Execute the application

The application can be executed by the command

```
./mvnw spring-boot:run
```

## pgadmin4

You can inspect the data stored in the db by using pgadmin4. It is available under localhost:5050.

Use the credentials

```
User: admin@admin.com
Password: admin
```


And create a connection for a server on the host postgres:5432 with user postgres and the password postgres


# Perform http requests

If you use intellij you may use the predefined http requests in the file _requests.http_ to explore the api.