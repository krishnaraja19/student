# student

## Student managment REST Api.

### Used Spring starter:
1.spring-boot-starter-web<br>
2.spring-boot-starter-data-jpa<br>
3.spring-boot-devtools<br>
4.spring-boot-docker-compose<br>
5.spring-boot-starter-test<br>
6.spring-boot-starter-validation<br>
7.spring-boot-starter-log4j2<br>
8.springdoc-openapi-starter-webmvc-ui<br>

### Other:
1.Lombok<br>
2.h2 (only for testing purpose.)<br> 
3.postgresql<br>

## Steps to Run the student application.

1. Please install the docker desktop
2. Install Java 17
3. There is no need to install a database because this project
   is integrated with spring-boot docker-compose.
   It will take the necessary db properties from the docker-compose.yml file. And Automatically pulled the database into the Local DockerDesktop.
4. Once the first three step is finished. 
   Please open this project By IntelliJ IDE or any IDE is fine.
5. Please run the main Java class in this project. The class name is StudentApplication.java file.
6. The StudentApplication.java class is located in com.assignment.student package.
7. If you need REST endpoint details. Please access the below open API documentation link.
http://localhost:8080/swagger-ui/index.html#/