package com.thinkit.cloud.upm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 configuration
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {

	/**
	 * 是否启用swagger
	 */
	@Value("${swagger2.enable:false}")
    private boolean swagger2Enable;

	/**
	 * 扫描包
	 */
	@Value("${swagger2.swagger2BasePackage}")
    private String swagger2BasePackage;
	
	/**
	 *swagger标题
	 */
	@Value("${swagger2.title}")
	private String swagger2Title;
	
	/**
	 *swagger描述
	 */
	@Value("${swagger2.description}")
	private String swagger2description;
	
	/**
	 *swagger版本
	 */
	@Value("${swagger2.version}")
	private String swagger2Version;
	
    @Bean
    public Docket createRestApi() {
        //添加head参数start
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(swagger2Enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger2BasePackage))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

  //添加ResourceHandler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
	
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }
 
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }
 
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title(swagger2Title)
        		.description(swagger2description)
                .version(swagger2Version)
                .build();
    }
}
