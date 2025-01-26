package com.goodvideo.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.goodvideo.upload.gateways.database.repositories")
@EnableScheduling
public class UploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadApplication.class, args);
	}

}
