# encoded-data-differ
Code challenge for Software Engineer position at WAES. 

## Assignment

- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
o `<host>/v1/diff/<ID>/left` and `<host>/v1/diff/<ID>/right`
- The provided data needs to be diff-ed and the results shall be available on a third end
point o `<host>/v1/diff/<ID>`
- The results shall provide the following info in JSON format 
  - If equal return that
  - If not of equal size just return that
  - If of same size provide insight in where the diffs are, actual diffs are not needed. 
    So mainly offsets + length in the data
- Make assumptions in the implementation explicit, choices are good but need to be
communicated

## Project
### Architecture
This project is created following clean architecture principles, using onion architecture
to separate concerns in 2 modules:
- Core: Business rules, entities definition and Services.
- Application: Defines data storage, API structure.

Benefits, context and considerations about implementing this architecture can be found in 
this [ADR](doc/ADR_001_04042021_clean_architecture.md).

### Deployment
**Using Docker:**

This project provides a [Makefile](Makefile) to make deploy process easier. Just need to 
  execute the following command in the root directory:
  
```shell
make run_docker
```

**Using gradle:**

Same as before, you can use the Makefile methods to execute the gradle commands and 
  deploy this application. If you want to execute it directly in your terminal using the gradle
wrapper:
    
- Compile sources  
```shell
./gradlew clean build
```

- cd into application directory

```shell
../gradlew bootRun
```

Application runs in the port 8080.

### Test
This project considers 2 kind of test:

**Unit Test**

Type the following command to execute all unit test from the code module:

```shell
./gradlew unitTest
```

**Integration Test**

Type the following command to execute all integration test from the application module:

```shell
./gradlew integrationTest
```

**Checkstyle**

In order to be aligned with good practices, the project uses checkstyle to scan for possible 
bad practices in Java syntax.

```shell
./gradlew checkstyleMain
./gradlew checkstyleTest
```

### Documentation
This project is documented using OpenAPI v3.0. If you want to know main benefits and 
some nice future improvements, visit the [ADR](doc/ADR_002_05042021_openapi_3.md)

The generated docs are available in:

http://localhost:8080/swagger-ui.html

Not all OpenAPI features were used, but if you need to generate the openapi.yml schema
to share the API definition, get it from:

http://localhost:8080/v3/api-docs.yaml

The application must be running to access the API docs.

**Code**

Models, Services, VO and Adapters are documented using the Javadoc format.

## Proposed Improvements

### API Resource definition
In order to follow RESTful good practices, I found the resource definitions `<host>/v1/diff/<ID>/right` 
and `<host>/v1/diff/<ID>/left` redundant, due to they refer to the same resource.

Instead, I propose to have the following resource definition for API:

Save new element
```
URL: localhost:8080/v2/element
Method: POST 
Body: {"side":"LEFT", "data" : "ZW5jb2RlZCBtZXNzYWdl"}
```

Update stored element
```
URL: localhost:8080/v2/element/{ID}
Method: PATCH 
Body: {"side":"LEFT", "data" : "ZW5jb2RlZCBtZXNzYWdl"}
```

Get differences
```
URL: localhost:8080/v2/element/{ID}/diff
Method: GET
```

**Benefits from this approach**

- Resource definition clearer. We are creating, updating and getting the diff resource.
- Easy to scale up: We can add more methods using the current resource definition.
- With the initial URI, the client does not require routing information.

Find the proposed API definition in the 
[V2 documents](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/v2) (
Application must be running to access). 
