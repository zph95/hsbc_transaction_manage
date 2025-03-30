package com.zeno.hsbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class HsbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsbcApplication.class, args);
	}

}
