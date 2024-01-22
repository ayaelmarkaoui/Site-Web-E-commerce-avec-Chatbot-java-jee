package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.library.*", "com.example.admin.*"})
@EnableJpaRepositories(value = "com.example.library.repositories")//Cette annotation est utilisée pour activer les fonctionnalités de gestion des repositories JPA (Java Persistence API) dans Spring Data JPA.
@EntityScan(value = "com.example.library.entities")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
