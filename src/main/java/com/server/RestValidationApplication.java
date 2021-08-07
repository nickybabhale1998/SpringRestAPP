package com.server;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
public class RestValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestValidationApplication.class, args);
	}

	// http://localhost:1020/swagger-ui.html#/Employee/
	//updated 
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.server.controller")).build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Employee CRUD API Info", "API Doumentation ", "1.0", "",
				new springfox.documentation.service.Contact(null, null, null), "API License", "http://localhost/1020",
				Collections.emptyList());
	}

}
