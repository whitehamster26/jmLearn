package ru.dsemenko.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.dsemenko.springboot.web")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
