# AcmeDates

This microservice is used to both receive and return information about delivery dates, storing it into a Postgres db.

## Development

To start your application in the dev profile, run:

    ./gradlew

Before that, don't forget to run the Postgres container described in `src/main/docker/docker-compose.yml` with

    docker-compose up -d
