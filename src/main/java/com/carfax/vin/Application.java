package com.carfax.vin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.carfax.vin.model.VinData;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
