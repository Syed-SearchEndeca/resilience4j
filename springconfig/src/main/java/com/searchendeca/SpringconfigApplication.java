package com.searchendeca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringconfigApplication.class, args);
	}

}
