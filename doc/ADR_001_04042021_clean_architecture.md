# Enforce Clean Architecture

## Context
Given the proposed scenario to implement, the application must perform storage and validation 
operations to encrypted data.

## Decision
In order to make the project scalable and enforce the clean architecture principles, I decided 
to adopt Onion Architecture instead of the common three layer to separate concerns about domain 
and application. Some main benefits:

- Maintainability: The onion model allows us to perform updates to application components without 
involving business rules related components. 
- Tests: We can implement tests for each layer separating business case tests - application behavior
tests. Easier to apply TDD.
  
Following this approach, the project will be composed of 2 modules:
- Core: For business rules and contract definition for business use cases.
- Application: To define data storage and API structure.

**Considerations**:
The `core` module serves as internal dependency for `application`. The goal is to maintain business logic
completely decoupled from the application/infrastructure code. 

## Reference
- [Microservices: Process, rules and scalability](https://medium.com/quintoandar-tech-blog/rethinking-microservices-process-rules-and-scalability-fd959b4e2ea)
- [Martin Fowler: Domain Driven Design - Value Objects](https://martinfowler.com/bliki/EvansClassification.html)

## Status
Accepted