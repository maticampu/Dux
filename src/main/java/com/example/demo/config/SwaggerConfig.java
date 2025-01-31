package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API EQUIPOS",
                version = "1.0.0",
                contact = @Contact(
                            name = "Matias CAmpuzano"
                        )
        ),
        servers = {
                @Server(
                        description = "localhost",
                        url="http://localhost:8080"
                )
        }
)
public class SwaggerConfig {}
