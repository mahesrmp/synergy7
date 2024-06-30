package com.example.xx2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.xx2"})
public class Xx2Application {

	public static void main(String[] args) {
		SpringApplication.run(Xx2Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
