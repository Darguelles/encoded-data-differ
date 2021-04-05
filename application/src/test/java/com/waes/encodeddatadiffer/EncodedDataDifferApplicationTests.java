package com.waes.encodeddatadiffer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.waes.encodeddatadiffer")
@EnableJpaRepositories(basePackages = "com.waes.encodeddatadiffer.repository")
public class EncodedDataDifferApplicationTests {

	public static void main(final String[] args) {
		SpringApplication.run(EncodedDataDifferApplicationTests.class, args);
	}

}
