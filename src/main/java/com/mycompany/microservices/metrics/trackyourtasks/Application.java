package com.mycompany.microservices.metrics.trackyourtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Bootstrap Class, starting up Spring Boot Application.
 * You can define a custom application configuration file using on jar execution the JVM option : "-Dspring.config.location=....."
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Application {

    /**
     * Main Class method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}