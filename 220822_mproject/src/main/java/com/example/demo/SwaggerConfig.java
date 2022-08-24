package com.example.demo;

import org.springframework.context.annotation.*;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false).select().apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any()).build().pathMapping("/").apiInfo(apiInfo());
    }
    // api의 그룹명이나 이동경로 보여질 api가 속한 패키지 등 정보 저장

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ZProject API Document").description("ZProject REST API 설명").version("1.0").build();
        // title : 메인화면 이름   // description : 하단 내용 설명
    }
}
