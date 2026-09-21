package com.victor.customerservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // <-- Required for your Service Discovery chapter
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
