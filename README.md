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

### Development
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

Open http://localhost:8080

### Production
* Build Web image (Docker)
* Run Compose (or do it you own way)

#### Build Web image
Vaadin has a production profile we will use with maven. \
We use Spring Boot Plugin to build Docker image
```
> mvn -Pproduction spring-boot:build-image
```
#### Docker Compose
Command docker-compose will start up both database and webapplication. \  
Run from root directory or use `docker-compose -y <docker-compose.yml>`

To start application run
```
> docker-compose up
```

To shutdown application run
```
> docker-compose down
```

Open http://localhost:8080