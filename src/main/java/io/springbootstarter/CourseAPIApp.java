package io.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CourseAPIApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseAPIApp.class,args);
    }
}
