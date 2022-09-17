package com.projects.proj2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Proj2021Application {

	public static void main(String[] args) {
		SpringApplication.run(Proj2021Application.class, args);
	}
    //documentation urls
	//http://localhost:8080/v2/api-docs
	//http://localhost:8080/swagger-ui/

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
