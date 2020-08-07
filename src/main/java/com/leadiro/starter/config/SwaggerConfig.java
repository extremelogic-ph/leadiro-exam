package com.leadiro.starter.config;

import java.util.Arrays;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SwaggerConfig.class);

    /**
     * Swagger description.
     */
    @Value("${exam.swagger.info.description:TODO change description}")
    private String description;

    /**
     * Swagger host.
     */
    @Value("${exam.swagger.host:localhost:8080}")
    private String host;

    /**
     * Swagger title.
     */
    @Value("${exam.swagger.info.title:TODO change title}")
    private String title;

    /**
     * Swagger version.
     */
    @Value("${exam.swagger.info.version:TODO change version}")
    private String version;

    /**
     * Swagger terms of service.
     */
    @Value("${exam.swagger.info.termsOfService:TODO change TOS}")
    private String termsOfService;

    /**
     * Swagger contact name.
     */
    @Value("${exam.swagger.info.contact.name:TODO change name}")
    private String contactName;

    /**
     * Swagger contact URL.
     */
    @Value("${exam.swagger.info.contact.url:TODO change url}")
    private String contactUrl;

    /**
     * Swagger contact email.
     */
    @Value("${exam.swagger.info.contact.email:TODO change email}")
    private String contactMail;

    /**
     * Swagger license.
     */
    @Value("${exam.swagger.info.license:TODO change license}")
    private String license;

    /**
     * Swagger license URL.
     */
    @Value("${exam.swagger.info.licenseUrl:TODO change license url}")
    private String licenseUrl;

    /**
     * Docker prerequisite.
     * @return Docket details
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.host(host)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.leadiro.starter.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(basicAuthScheme()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                title,
                description,
                version,
                termsOfService,
                new Contact(contactName,
                        contactUrl,
                        contactMail),
                license,
                licenseUrl,
                Collections.emptyList());
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }
}
