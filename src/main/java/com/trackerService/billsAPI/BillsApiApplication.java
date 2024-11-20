package com.trackerService.billsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.trackerService.billsAPI")
public class BillsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsApiApplication.class, args);
	}

}
