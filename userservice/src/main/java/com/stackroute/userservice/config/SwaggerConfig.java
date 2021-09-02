package com.stackroute.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;

/*As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */
@Configuration
@EnableSwagger2

public class SwaggerConfig {

	/*
	 * Annotate this method with @Bean . This method will return an Object of Docket.
	 * This method will implement logic for swagger
	 */

@Bean
	public Docket productApi() {
	return new Docket(DocumentationType.SWAGGER_2)
		     .select()
		     .apis(RequestHandlerSelectors.basePackage("com.stackroute.userservice"))
		      .build()
		      .apiInfo(getapiinfo())
		      .useDefaultResponseMessages(false);
	}

public ApiInfo getapiinfo()
{
	ApiInfoBuilder apibuild=new ApiInfoBuilder();
	apibuild.title("User Auth App")
	.description("This API helps to register and authenticate");
	
	
	return apibuild.build();
}
	}


