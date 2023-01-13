package ru.savosin.databasewebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DatabaseWebApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseWebApplication.class, args);
    }


}
