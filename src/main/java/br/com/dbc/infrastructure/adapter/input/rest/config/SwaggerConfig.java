package br.com.dbc.infrastructure.adapter.input.rest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "DBC - Gestão de pautas e sessões de votação",
                description = "API para gerenciar pautas e sessões de votação"
        )
)
@Configuration
public class SwaggerConfig {
}
