package com.mycompany.microservices.metrics.trackyourtasks;

import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * Main Bootstrap Class, starting up Spring Boot Application.
 * You can define a custom application configuration file using on jar execution the JVM option : "-Dspring.config.location=....."
 * @author Fabrizio Torelli (hellgate75@gmail.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class Application {

    /**
     * This method that define the application configuration location based on a system
     * environment variable : APP_ENV, if the variable doesn't exist the system load
     * the default jar H2 embedded configuration, else-wise we have to define in the
     * same jar folder a specific configuration file named application-{APP_ENV lower case}.properties
     * in this case we have a specific deployment of the application.
     * @return PropertySourcesPlaceholderConfigurer Application configuration location
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        String environment = System.getenv("APP_ENV");
        if (environment == null) environment = "";
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        LogManager.getLogger(Application.class).info("Environment=" + environment);
        if (environment.trim().length()>0) {
            properties.setLocation(new FileSystemResource("application-"+environment.toLowerCase()+".properties"));
        } else {
            properties.setLocation(new ClassPathResource("application.properties"));
        }
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
    /**
     * Main Class method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}