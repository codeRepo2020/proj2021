package com.projects.proj2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
public class Proj2021Application {

	public static void main(String[] args) {
		SpringApplication.run(Proj2021Application.class, args);
	}


}
