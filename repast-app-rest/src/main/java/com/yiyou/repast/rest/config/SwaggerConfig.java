package com.yiyou.repast.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 config
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String SCAN_API_PACKAGE="com.yiyou.repast.rest.controller";

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(SCAN_API_PACKAGE)).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("爱扫码Restful API接口文档")
				.description("简单优雅高效的接口文档，您值得拥有。")
				.termsOfServiceUrl("http://www.read.com").version("1.0").build();
	}

}
