package com.example.holidaywork.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启Swagger
//Swagger配置类
public class SwaggerConfig {

    @Bean
    //注入Docket实例（它负责Swagger的配置）
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("默认分组")  //设置文档分组名称
                .apiInfo(apiInfo())    //设置api介绍
                .select()
                /**RequestHandlerSelectors -> 什么样的类要添加到swagger 有如下几种方式：
                 * basePackage:扫描指定的包（用的较多）
                 * any：全部
                 * none：无
                 * witClassAnnotation：有指定ClassAnnotation的类
                 * withMethodAnnotation：有指定MethodAnnotation的类(用的较多)
                 */
                .apis(RequestHandlerSelectors.basePackage("com.example.holiday"))
                /**PathSelectors -> 什么样的请求要被添加 有如下几种方式
                 * any：添加全部（用的多）
                 * none：无
                 * antPattern：扫描指定路径（如果参数为/test/**，则只添加以test开头的请求）
                 */
                .paths(PathSelectors.any())
                .build();
    }

    //配置Swagger封面信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("leatral", "", "");

        return new ApiInfo(
                "黑马旅游网",    //工程名
                "= 这里是工程描述 =",  //工程描述
                "1.0",  //工程版本
                "http://localhost:8080/",  //团队地址
                contact,    //作者信息
                "Apache 2.0",   //开源协议
                "http://www.apache.org/licenses/LICENSE-2.0",   //开源协议的url
                new ArrayList()
        );
    }
}
