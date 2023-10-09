package com.assignment.student.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI schoolMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Student Management")
                        .description("You can able to manage student data here....")
                        .version("1.0"));
    }
}
