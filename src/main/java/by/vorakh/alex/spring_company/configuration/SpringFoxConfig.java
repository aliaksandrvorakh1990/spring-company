package by.vorakh.alex.spring_company.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig { 
                                  
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
    
    private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Company REST API", 
	      "Company is how you store information about human resources. "
	      + "<br/>Use the Company API calls to manage information about companies "
	      + "and their employees in your database.", 
	      "1.0.0", 
	      "http://localhost:8080/terms_and_conditions", 
	      new Contact("Alexander Vorakh", 
		      "https://github.com/aliaksandrvorakh1990/spring-company", 
		      "aliaksandrvorakh@gmail.com"), 
	      "The Open Software License 3.0 (OSL-3.0)",
	      "https://opensource.org/licenses/OSL-3.0",
	      Collections.emptyList());
    }
    
}