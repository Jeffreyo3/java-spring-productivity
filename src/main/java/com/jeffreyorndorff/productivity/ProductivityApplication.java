package com.jeffreyorndorff.productivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "file:/Users/Izlix/productivity.properties", ignoreResourceNotFound = true)
public class ProductivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductivityApplication.class, args);
    }

}
