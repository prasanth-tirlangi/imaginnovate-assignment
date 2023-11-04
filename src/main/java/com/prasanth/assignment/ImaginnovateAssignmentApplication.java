package com.prasanth.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({ "com.prasanth.assignment.repository" })
@EntityScan(basePackages = { "com.prasanth.assignment.entity" })
public class ImaginnovateAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImaginnovateAssignmentApplication.class, args);
	}

}
