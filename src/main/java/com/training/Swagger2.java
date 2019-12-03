package com.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
//    @Bean
//    public Docket createRestApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.training.backend.demo"))
//                .paths(PathSelectors.any())
//                .build();
//    }
@Bean
public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .pathMapping("/")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.training"))
            .paths(PathSelectors.any())
            .build().apiInfo(new ApiInfoBuilder()
                    .title("SpringBoot整合Swagger")
                    .description("SpringBoot整合Swagger，详细信息......")
                    .version("9.0")
                    //.contact(new Contact("啊啊啊啊", "blog.csdn.net", "aaa@gmail.com"))
                    .license("The Apache License")
                    .licenseUrl("http://www.baidu.com")
                    .build());
}
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Private car for public use")
                .description("项目里包含的各种接口")
                .termsOfServiceUrl("url")
                .contact("contact")
                .version("1.0")
                .build();
    }
}