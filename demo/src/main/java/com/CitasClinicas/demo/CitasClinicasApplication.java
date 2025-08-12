package com.CitasClinicas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.CitasClinicas.demo.Repositorios")
@EntityScan(basePackages = "com.CitasClinicas.demo.Modelos")
public class CitasClinicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasClinicasApplication.class, args);
	}

}
