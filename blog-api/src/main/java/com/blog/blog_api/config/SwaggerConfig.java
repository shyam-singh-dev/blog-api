package com.blog.blog_api.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Blog API")
                .version("1.0")
                .description("REST API for blog management with " +
                 "JWT authentication, JPA relationships, " +
                 "comments, likes, search, and pagination.")
                .contact(new Contact().name("Shyam Singh Parmar")
                        .email("shyamparmar.dev@gmail.com"))
                .license(new License().name("MIT License")))
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication")).components(new Components()
                .addSecuritySchemes("Bearer Authentication",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("Bearer")
                                .description("Enter JWT token here")));
    }

}
