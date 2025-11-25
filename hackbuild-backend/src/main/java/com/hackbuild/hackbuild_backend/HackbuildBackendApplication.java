package com.hackbuild.hackbuild_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hackbuild.hackbuild_backend")
public class HackbuildBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackbuildBackendApplication.class, args);
	}
}
