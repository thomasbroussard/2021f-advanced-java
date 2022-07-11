package fr.epita.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class);
    }
}
