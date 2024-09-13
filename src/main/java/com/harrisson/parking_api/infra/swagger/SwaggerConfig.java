package com.harrisson.parking_api.infra.swagger;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Parking API")
                        .description("API Rest para gerenciar de estacionamentos\n\n Siga até Authentication, e utilize o usuário padrão descrito na documentação")
                        .contact(new Contact()
                                .email("harrissondutra@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://parking-api/api/licenca")));
    }

    @Bean
    public OpenApiCustomizer filterControllers(RequestMappingHandlerMapping handlerMapping) {
        return openApi -> {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
                if (!handlerMethod.getBeanType().isAnnotationPresent(Tag.class)) {
                    Set<String> patterns = requestMappingInfo.getPatternsCondition() != null ? requestMappingInfo.getPatternsCondition().getPatterns() : null;
                    if (patterns != null) {
                        patterns.forEach(openApi.getPaths()::remove);
                    }
                }
            });
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Allow all origins
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
