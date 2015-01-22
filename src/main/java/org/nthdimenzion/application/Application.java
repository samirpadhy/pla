package org.nthdimenzion.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Samir
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pla","org.nthdimenzion"})
@EntityScan(basePackages = {"com.pla"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
