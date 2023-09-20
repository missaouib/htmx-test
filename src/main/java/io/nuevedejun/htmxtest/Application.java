package io.nuevedejun.htmxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	/*
	 * TODO
	 *  * Microservices: separate the following authentication, operations/transactions (protobuf), frontend rendering
	 *  + Stateless sessions (redis or something)
	 */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
