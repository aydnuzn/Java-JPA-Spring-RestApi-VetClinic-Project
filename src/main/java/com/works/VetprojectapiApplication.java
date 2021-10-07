package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "com.works.repositories")
@SpringBootApplication
public class VetprojectapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetprojectapiApplication.class, args);
    }

}
