package com.dib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

/**
 * @author Dibyaprakash
 * 
 * This component contains all swagger related configurations
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("note-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/note.*"), regex("/api/user.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("NoteApp API")
				.description("NoteApp API reference for developers")
				.termsOfServiceUrl("http://localhost:3535").license("Geekyants License")
				.version("1.0").build();
	}

}