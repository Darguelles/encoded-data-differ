package com.waes.encodeddatadiffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.waes.encodeddatadiffer")
@EnableJpaRepositories(basePackages = "com.waes.encodeddatadiffer.repository")
public class EncodedDataDifferApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncodedDataDifferApplication.class, args);
    }

}
