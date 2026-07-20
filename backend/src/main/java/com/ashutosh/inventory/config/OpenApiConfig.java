package com.ashutosh.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Inventory Management System API")

                        .description("REST API documentation for Inventory Management System built using Spring Boot.")

                        .version("1.0.0")

                        .contact(new Contact()
                                .name("Ashutosh Gajankush")
                                .email("ashutoshgajankush6219@gmail.com"))

                        .license(new License()
                                .name("MIT License")))

                .externalDocs(new ExternalDocumentation()

                        .description("GitHub Repository")
                        .url("https://github.com/ashu-6219/Inventory-Management-System"));
    }

}