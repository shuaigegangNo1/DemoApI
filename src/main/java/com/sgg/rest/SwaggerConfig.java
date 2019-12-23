package com.sgg.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {


//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
//    }
//
//
//    @Bean
//    public Docket createRestApi() {
//
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("token").description("用户登录token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
//
//        return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .select()
//            //加了ApiOperation注解的类，才生成接口文档
//            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//            //包下的类，才生成接口文档
//            //.apis(RequestHandlerSelectors.basePackage("io.renren.controller"))
//            .paths(PathSelectors.any())
//            .build()
//                .globalOperationParameters(pars)
//            .securitySchemes(security())
//                ;
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("人脸围棋系统")
//            .description("接口文档")
//            .termsOfServiceUrl("#")
//            .version("1.0")
//            .build();
//    }
//
//    private List<ApiKey> security() {
//        return newArrayList(
//            new ApiKey("access_token", "access_token", "header")
//        );
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("API接口文档")
//                .description("XXX系统")
//                .version("1.0.0")
//                .build();
//    }
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.sgg.rest.controller")) //这里写的是API接口所在的包位置
// 
//                .paths(PathSelectors.any())
//                .build();
//    }
	/**
     * 指定扫描包的路径来指定要创建API的目录，一般是控制器这个包
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("token").description("用户登录token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sgg.rest.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(setHeaderToken());
    }

    /**
     * 设置API的基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("XXX系统")
                .termsOfServiceUrl("https://www.campusbell.cn")
                .version("1.0.0")
                .build();
    }
    

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add((Parameter) tokenPar.build());
        return pars;
    }
    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "Authorization", "header"));
 }
}
