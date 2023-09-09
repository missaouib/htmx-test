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

> **NOTE:** above command only kind of sort of works. Intellij won’t HotSwap class changes even if the run configuration
> is executed in debug mode. What I found works best for me is to have a run configuration running for `npm run watch`
> and another one running the Spring Boot app in debug mode.

## Thoughts

### Primary keys

There is a lot of information about trade-offs of using UUID as primary keys. Seems like consensus is that UUID v4 are
not a good fit for primary keys due to their randomness. Some alternatives use sequential UUID (such as v6) to overcome
the limitations.

After some consideration, I decided to use UUID v6 to prepare in case I need unique keys across databases or
systems sometime.

As a reminder for myself: UUIDs are not secure. I should treat them as guessable and implement correct access controls
to mitigate attack vectors.
