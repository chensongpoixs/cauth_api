package com.jpa.springboot_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(new DocumentationType("SWAGGER", "2.0"))
                .apiInfo(getApiInfo())
//                .host("http://localhost:8080")
                .groupName("SP-JPA")
                //.globalResponseMessage(RequestMethod.GET, Lists.newArrayList())

                //.pathMapping("AAAAA")// 上下文
                .enableUrlTemplating(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jpa.springboot_jpa.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("SP-JPA", "https://gitee.com/profile/repositories", "xyg15243228311@163.com");
        return new ApiInfo("swagger文档", "描述", "2.0", "https://gitee.com/hecanhc/dashboard/projects",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }

}
