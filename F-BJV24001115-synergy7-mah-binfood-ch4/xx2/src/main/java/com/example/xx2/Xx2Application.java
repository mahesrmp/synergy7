package com.example.xx2;

import com.example.xx2.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.xx2"})
public class Xx2Application {

	public static void main(String[] args) {
		HomeController homeController = SpringApplication.run(Xx2Application.class, args).getBean(HomeController.class);
		homeController.home();
	}

}
