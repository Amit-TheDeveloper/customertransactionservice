package com.enterprise.payments.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * Boots a SprintBoot application
 *
 * @author amit chandra
 */
@SpringBootApplication
public class CustomerTransactionServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CustomerTransactionServiceApplication.class, args);
	}

	/**
	 * Initializes a model mapper to be used for bean mappings.
	 * @return
	 */
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
