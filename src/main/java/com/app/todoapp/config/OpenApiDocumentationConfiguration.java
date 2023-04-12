package com.app.todoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
            title = "Todo App Swagger",
            version = "1.0",
            description = "this API represent the CRUD operations on tasks and each user have multiple tasks and he can Insert , Updated , Delete Any of Them"
        )
)
@Configuration
public class OpenApiDocumentationConfiguration {
}
