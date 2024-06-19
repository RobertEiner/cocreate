package com.cocreate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CocreateApplication {

	public static void main(String[] args) {
		// this is where the application context is created. The application context represents the IoC container.
		SpringApplication.run(CocreateApplication.class, args);
	}

}
