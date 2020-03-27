package com.sgg.rest.security;


import static com.sgg.rest.security.SecurityConstants.SIGN_UP_URL;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sgg.rest.CORSFilter;
import com.sgg.rest.service.LoginService;




@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	//todo : remove 
    private UserDetailsService userDetailsService;

    private TokenHelper tokenHelper;
    
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    private CORSFilter corsFilter;
    
    private LoginService loginService;
    
    public WebSecurity(UserDetailsService userDetailsService, TokenHelper tokenHelper
    		, AuthenticationSuccessHandler authenticationSuccessHandler, CORSFilter corsFilter 
    		, LoginService loginService 
    		) 
    {
        this.userDetailsService = userDetailsService;
        this.tokenHelper = tokenHelper;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.corsFilter = corsFilter;
        this.loginService = loginService;
    }

    //TODO profile based config
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests()
        		.antMatchers(HttpMethod.OPTIONS).permitAll() //seems not needed now
        		.antMatchers("/windbell/**").permitAll()
        		.antMatchers("/msg/**").permitAll() 
        		.antMatchers("/menu/**").permitAll() 
//        		.antMatchers("/test/**").permitAll()
        		.antMatchers("/org/**").permitAll()
        		.antMatchers("/sysLog/**").permitAll()
        		.antMatchers("/user/**").permitAll()
        		.antMatchers("/role/**").permitAll()
        		.antMatchers("/common/**").permitAll() 
        		.antMatchers(
                        "/swagger-ui.html",
                        "/v2/api-docs", // swagger api json
                        "/swagger-resources/configuration/ui", // 用来获取支持的动作
                        "/swagger-resources", // 用来获取api-docs的URI
                        "/swagger-resources/configuration/security", // 安全选项
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(loginService, authenticationSuccessHandler), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(tokenHelper, loginService), JWTAuthenticationFilter.class);

    }
    

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //not working, instead add corsFilter in configure(HttpSecurity http)
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(ImmutableList.<String>of("Authorization, Access-Control-Request-Method, Origin, X-Requested-With, Content-Type, Accept,jwt,x-auth-token"));
//        corsConfig.setAllowedOrigins(ImmutableList.of("*"));
//        corsConfig.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        
//        source.registerCorsConfiguration("/**", corsConfig);
//        return source;
//    }
}
