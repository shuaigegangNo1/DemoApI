package com.sgg.rest;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableCaching
@MapperScan("com.sgg.rest.dao")
public class Application {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
    	

    	SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
    	builder.bannerMode(Banner.Mode.CONSOLE).run(args);
    }
}
