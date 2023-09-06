# htmx-test

This project serves a web page using Thymeleaf for templating and server side rendering, and htmx for client side
interactivity.

The client code is bundled with vite to take advantage of npm packages and Typescript to add fine-grained client
functionality.

## Dependencies

- Java `20`
- node `v18`, npm `^9.8.1`
- docker compose

## Start

Postgres DB must be running for the service to start successfully. `cd` into `docker/` and run:

```bash
docker compose up -d
```

During development, start the project using the Spring Boot Maven plugin and the Maven profile `npm-watch` to monitor
file changes in the web project and enable live reload:

```bash
./mvnw spring-boot:run -Pnpm-watch
```
