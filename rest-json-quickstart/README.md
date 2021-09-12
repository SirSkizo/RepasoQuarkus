# code-with-quarkus Project

Table of Contents
=================

   * [code-with-quarkus Project](#code-with-quarkus-project)
      * [Requirements](#requirements)
         * [PostgreSQL database](#postgresql-database)
      * [Running the application in dev mode](#running-the-application-in-dev-mode)
      * [Packaging and running the application](#packaging-and-running-the-application)
      * [Creating a native executable](#creating-a-native-executable)
      * [Provided Code](#provided-code)
         * [RESTEasy JAX-RS](#resteasy-jax-rs)

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Requirements

### PostgreSQL database

This Quarkus application uses a PostgreSQL database for the ORM operations. This database can be created using [Podman](https://podman.io/) and these are the instructions.

Remove an existing POD if it exists.

```bash
$ podman pod rm quarkus_test_db_pod
```

If the POD is running and the remove operation fails either stop the pod first or use the `-f` flag to force the POD removal.

Create a new POD mapping the internal `5432` PostgreSQL port with the host `5432` which is being used in the [application.properties](src/main/resources/application.properties) file.

```bash
$ podman pod create -p 5432:5432 --name quarkus_test_db_pod
```

Start a PostgreSQL container on the newly created POD using as database, user and password the same values used in the [application.properties](src/main/resources/application.properties) file.

```bash
$ podman run --name quarkus_test_db --pod quarkus_test_db_pod -d -e POSTGRES_DB=hibernate_db -e POSTGRES_USER=hibernate -e POSTGRES_PASSWORD=hibernate postgres:latest
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
