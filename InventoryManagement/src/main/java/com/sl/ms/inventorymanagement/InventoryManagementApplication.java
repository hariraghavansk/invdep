package com.sl.ms.inventorymanagement;


import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails());
	}
	
	public ApiInfo apiDetails() {
		return new ApiInfo(
				"Inventory Management API",
				"Inventory services",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Hariraghavan SK","http://Localhost:8889/dev/api","hsk@ms.com"),
				"API License",
				"http://Localhost:8889/dev/api",
				Collections.emptyList());
							
	}
	
}
