package com.backbase.tinyexpression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Spring boot application for tiny expression.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class TinyExpressionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyExpressionApplication.class, args);
    }
}
