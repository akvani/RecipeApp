package com.stackroute.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.stackroute.favouriteservice.jwtfilter.JwtFilter;



@SpringBootApplication
public class FavouriteServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FavouriteServiceApplication.class, args);
		
	}

	
	@Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
UrlBasedCorsConfigurationSource urlcorsobj=new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsconfig=new CorsConfiguration();
		
		corsconfig.setAllowCredentials(true);
		corsconfig.addAllowedOrigin("*");
		corsconfig.addAllowedMethod("*");
		corsconfig.addAllowedHeader("*");

		urlcorsobj.registerCorsConfiguration("/**", corsconfig);	
		
		FilterRegistrationBean fbean=new FilterRegistrationBean<JwtFilter>(new JwtFilter());
		
		fbean.addUrlPatterns("/api/v1/*");
		
		return fbean;
    }

	
}

