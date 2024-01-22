package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.client","com.example.library"})
@EnableJpaRepositories(value = "com.example.library.repositories")//Cette annotation est utilisée pour activer les fonctionnalités de gestion des repositories JPA (Java Persistence API) dans Spring Data JPA.
@EntityScan(value = "com.example.library.entities")
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
