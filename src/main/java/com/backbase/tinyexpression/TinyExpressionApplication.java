package com.backbase.tinyexpression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring boot application for tiny expression.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class TinyExpressionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyExpressionApplication.class, args);
    }
}
