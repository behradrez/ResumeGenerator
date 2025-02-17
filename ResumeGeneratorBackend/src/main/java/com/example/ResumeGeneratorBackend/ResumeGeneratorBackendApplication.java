package com.example.ResumeGeneratorBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ResumeGeneratorBackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(ResumeGeneratorBackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResumeGeneratorBackendApplication.class, args);
	}

}
