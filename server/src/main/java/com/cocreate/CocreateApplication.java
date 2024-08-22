package com.cocreate;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CocreateApplication {

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		// this is where the application context is created. The application context represents the IoC container.
		SpringApplication.run(CocreateApplication.class, args);
	}

}
