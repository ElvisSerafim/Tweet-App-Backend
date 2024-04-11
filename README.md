# Tweet App Backend

This project uses Quarkus, the Supersonic Subatomic Java Framework, SurrealDB Database and Temporal.io for manage orchestration workflows.

## Requirements

You need to have SurrealDB and Temporal.io instances running on your local machine.

```bash

# For the SurrealDB, run the command on the command line:
$ docker run --rm --pull always -p 8000:8000 surrealdb/surrealdb:latest start
# It will run the SurrealDB docker image.

# Then, install Temporal.io on your machine, according to the operating system used using this link: https://docs.temporal.io/dev-guide/java/project-setup#install-cli

#After install the temporal.io, open the command line and run the command:
$ temporal server start-dev

#It will start the local development server.
```

## Running the project

```bash
# Clone the repository
$ git clone <https://github.com/ElvisSerafim/Tweet-App-Backend.git>

# Access the project folder
$ cd tweetapp

# Run the project. The project will run on http://localhost:8080
$ ./mvnw compile quarkus:dev
```
