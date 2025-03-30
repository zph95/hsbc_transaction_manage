- Introduction:
    - The project needs Java 21 environment
    - The project is powered by Spring Boot, Mybatis plus and H2 database
    - The project exposes REST APIs for the CRUD operations, and also provides open api docs.
    - The project is built using Maven
- Service information:
    - Listening port: 8080
    - Servlet path: /hsbc
    - Swagger ui path: /hsbc/swagger-ui.html
- Unit tests script:
    - src/test/java/com/zeno/hsbc/controller/TransactionControllerTest.java
- External dependencies:
    - Caffeine & Spring Cache: caches the data for faster access
    - Mybatis-plus: provides data persistence and page query Interceptor
    - H2 database: provides the in-memory database
    - SpringDoc OpenAPI: provides the api docs
    - Spring Boot: the web service framework
    - Lombok: provides some basic annotations
    - Spring Test: unit test engine
    - Spring Web: web service framework
    - Spring Boot Validation: provides the validation framework
- Deployment:
    - It is recommended to use docker to deploy the service
In the src folder, you can find the Dockerfile then run the following command to build the docker image and run the container
```bash
docker build -t hsbc .
docker run -p 8080:8080 hsbc
```
when finished, you can access the service via http://localhost:8080/hsbc/swagger-ui.html