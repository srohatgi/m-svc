package com.intocloudtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket dcp360api() {
        return new Docket(DocumentationType.SPRING_WEB)
                .securitySchemes(new ArrayList<ApiKey>() {{
                    add(new ApiKey("X-IT-Auth", "all", "header"));
                }})
                .groupName("web api")
                .select()
                .paths(regex("/api/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("web api")
                .description("manage your api")
                .termsOfServiceUrl("#")
                .contact("intocloudtech.com")
                .license("private")
                .version("0.0.1")
                .build();
    }

}
