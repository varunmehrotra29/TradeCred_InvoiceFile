package com.ECM.cartdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CartDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartDiscoveryServerApplication.class, args);
	}

}
