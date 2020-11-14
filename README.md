# Vaadin Project
Taking Vaadin for a little spin.
Created a Vaadin download from their start page https://start.vaadin.com/?preset=lts

* Selected Template Master-Detail
* Selected Type Java Only
## Requirements
* Java 11+
* Maven 3.5+
* Docker Desktop

## Using

#### Run PostgreSQL in Docker
```
> docker run --rm \ 
--name postgresql \
-e POSTGRESQL_USERNAME=vaadin \
-e POSTGRESQL_PASSWORD=vaadin \
-e POSTGRESQL_DATABASE=vaadin \
-p 5432:5432 \
bitnami/postgresql:latest
```

#### Run application
Note! It will take a while if it is your first run. A lot of files will be downloaded.
```
> mvn spring-boot:run
```

#### Open browser 
http://localhost:8080