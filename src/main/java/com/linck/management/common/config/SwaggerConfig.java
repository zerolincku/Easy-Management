package com.linck.management.common.config;

import com.linck.management.common.api.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-09 17:33
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 添加全局响应状态码
                .globalResponseMessage(RequestMethod.GET, responseMessageList())
                .globalResponseMessage(RequestMethod.POST, responseMessageList())
                .globalResponseMessage(RequestMethod.PUT, responseMessageList())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList())
                .select()
                //指定包下的controller生成API文档
                //.apis(RequestHandlerSelectors.basePackage("com.linck.mall.tiny.controller"))
                //为有注解@Api的controller生成API文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有注解@ApiOperation的方法生成API文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())

                .build()
                //添加登录认证
                .securitySchemes(securitySchems())
                .securityContexts(securityContexts());

    }

    private List<ResponseMessage>  responseMessageList(){
        //添加全局响应状态码
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Arrays.stream(ResultCodeEnum.values()).forEach(resultCodeEnum -> {
            responseMessageList.add(
                    new ResponseMessageBuilder().code(resultCodeEnum.getCode()).message(resultCodeEnum.getMessage()).responseModel(
                            new ModelRef(resultCodeEnum.getMessage())).build()
            );
        });
        return responseMessageList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("管理系统")
                //描述
                .description("Management-Doc")
                // 三个参数依次是姓名，个人网站，邮箱
                .contact(new Contact("linck", "", "zerolinck@foxmail.com"))
                //版本
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchems() {
        //设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}
