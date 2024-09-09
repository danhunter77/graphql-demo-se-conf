# GraphQL API Example & Workshop
This is an example implementation of a GraphQL API using Spring Boot and GraphQL DGS Library from Netflix

This example also demos Federated GraphQL with an Apollo Server

Documentation for the DGS framework can be found [here](https://netflix.github.io/dgs).

The repository contains three separate projects:

1. `customer-api`: A Java GraphQL service providing the `Customer` type
2. `agreement-api`: A Java GraphQL service that extends the `Customer` type with `Agreements`
3. `apollo-gateway`: An instance of Apollo Server acting as the Federated Gateway

The `customer-api` and `agreement-api` projects are Gradle projects.
The `apollo-gateway` is a Node project.

### Start Customer API
1. Either start application in an IDE or via the Terminal using `./gradlew bootRun`
2. Open http://localhost:8080 for the GraphiQL query editor

The following is a Customer query:
```graphql
query {
    customer(idFilter: 123) {
        id
        firstname
        lastname
        emailAddress
  }
}
```

### Start Agreement API
1. Either start application in an IDE or via the Terminal using `./gradlew bootRun`
2. Open http://localhost:8081 for the GraphiQL query editor

The following is an Agreement query:
```graphql
query {
    agreement(id: 2222) {
        id
        startDate
        endDate
        active
    }
}
```

## Federated GraphQL Query

### Start the apollo gateway for a Federated GraphQL example
3. Run `npm install` in the `apollo-gateway` project
4. Run `npm start` in the `apollo-gateway` project
5. Open http://localhost:4000 for the Apollo query editor

The following is a federated query:

```graphql
query {
    customer(idFilter: 123) {
        firstname
        lastname
        emailAddress
        vehicle {
            id
            make
            model
        }
        agreements {
            id
            startDate
            endDate
            active
        }
    }
}
```
