package com.abosancic.activemq;

import com.google.gson.Gson;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJms
@EnableSwagger2
@SpringBootApplication
public class SpringActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringActivemqApplication.class, args);
	}
    
    @Bean
    public Gson gson() {
        return new Gson();
    }
    
    @Bean //Don't forget the @Bean annotation
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abosancic.activemq"))
                .paths(PathSelectors.ant("/*"))
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "WiMed REST API",
                "Wimed service.",
                "API WiMed",
                "Terms of service",
                new Contact("Aleksandar Bosancic", "www.example.com", "aleksandar.bosancic@devlabs.ba"),
                "License of API", "API license URL", Collections.emptyList());
    }
    
}
