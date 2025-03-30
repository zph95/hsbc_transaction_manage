- Introduction:
    - The project is a demo transaction management system. it is a simple CRUD application that allows users to create, read, update, and delete transaction records.
- dependencies:
    - Java 21
    - Maven
    - Docker (if using Docker)
- Service information:
    - Listening port: 8080
    - Servlet path: /hsbc
    - Swagger ui path: /hsbc/swagger-ui.html
- Unit tests script:
    - src/test/java/com/zeno/hsbc/controller/TransactionControllerTest.java
- External dependencies:
    - Spring Web: web service framework
    - Spring Boot Validation: provides the validation framework
    - Spring Test: unit test engine
    - SpringDoc OpenAPI: provides the api docs
    - Caffeine & Spring Cache: caches the data for faster access
    - Mybatis-plus: provides data persistence and page query interceptor
    - H2 database: provides the in-memory database
    - Lombok: provides some basic annotations
- Deployment:
    - It is recommended to use docker to deploy the service.

In the src folder, you can find the Dockerfile then run the following command to build the docker image and run the container

```bash
docker build -t hsbc .
docker run -p 8080:8080 hsbc
```
when finished, you can access the service via http://localhost:8080/hsbc/swagger-ui.html

### Scaling Plan

1. **Read-Write Separation**  
   Configure a primary data source (for write operations) and a secondary data source (for read operations). Execute read-only interfaces on the secondary database, while all other operations are executed on the primary database.

2. **Partitioning by Time**  
   Partition the data based on the transaction time, storing the data in tables named `transaction_{yyyy}_{mm}`.

3. **Partitioning by User ID**  
   Partition the data by taking the `user_id` modulo 20 (or another number), storing the data in tables named `transaction_{user_id%20}`.

4. **Partitioning by Business Type**  
   Partition the data by business type (`type`), storing the data in tables named `transaction_{type}`.